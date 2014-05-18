/**
 * 
 */
package serv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import message.Message;
import pojo.User;
import typemessage.TypeMessage;
import utils.UtilsClientHandler;
import utils.UtilsHibernate;
import utils.UtilsMessage;

/**
 * @author Lwaxana
 *
 */
public class ClientHandler implements Runnable {

	private Socket socket;
	private Message message;
	private BufferedReader br;
	private BufferedWriter bw;
	private boolean connect;
	private Socket currentsocket;
	private SSLSocket sslsocket;
	private String string;
	private boolean auth;
	private User user;
	private boolean sslup;
	private TypeMessage typemessage;
	
	public ClientHandler(Socket socket){
		this.socket = socket;
		this.currentsocket = socket;
		connect = true;
		auth = false;
		sslup = false;
		setComm(currentsocket);	
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		while ( connect && !auth ){
			try {
				string = br.readLine();
				message = (Message) UtilsMessage.fromString(string);
				traiterMessage(message);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void traiterMessage(Message message){
		typemessage = message.getTypeMessage();
		if ( typemessage == TypeMessage.ConnexionStart){
			message = new Message();
			message.setTypeMessage(TypeMessage.ConnexionOk);
			envoyerMessage(message);
		}
		if ( typemessage == TypeMessage.Authentification){
			user = UtilsClientHandler.checkUser(message.getEmail());
			if ( user != null){
				if ( user.getPassword().equals(message.getPassword())){
					if ( !user.isOnline()){
						message = new Message();
						message.setTypeMessage(TypeMessage.Authentificationok);
						envoyerMessage(message);
						message = UtilsClientHandler.fillUserInfo(user);
						envoyerMessage(message);
						user.setSocket(socket);
						Thread t = new Thread(user);
						t.start();
						auth = true;
						
					}
					else{
						message = new Message();
						message.setTypeMessage(TypeMessage.AuthentificationFail);
						message.setMessage("deja online");
						envoyerMessage(message);
					}
					
				}
				else{
					message = new Message();
					message.setTypeMessage(TypeMessage.AuthentificationFail);
					message.setMessage("Mauvais password");
					envoyerMessage(message);
				}
			}
			else{
				message = new Message();
				message.setTypeMessage(TypeMessage.AuthentificationFail);
				message.setMessage("Utilisateur inconnu");
				envoyerMessage(message);
			}
		}
		if ( typemessage == TypeMessage.ConnexionStop){
			deconnex();
		}
		
		if ( typemessage == TypeMessage.NewUser){
			System.out.println("recu");
			user = UtilsClientHandler.checkUser(message.getEmail());
			if ( user == null){
				user = UtilsClientHandler.inscrire(message);
				if ( user == null ){
					message = new Message();
					message.setTypeMessage(TypeMessage.NewUserFail);
					message.setMessage("Erreur");
					envoyerMessage(message);
				}
				else{
					message = new Message();
					message.setTypeMessage(TypeMessage.NewUserOk);
					envoyerMessage(message);
				}
			}
			else{
				message = new Message();
				message.setTypeMessage(TypeMessage.NewUserFail);
				message.setMessage("Utilisateur existe déjà");
				envoyerMessage(message);
			}
		}
		
		
		
		
		
	}

	/**
	 * 
	 * @param message
	 */
	public void envoyerMessage(Message message){
		try {
			String tosend = UtilsMessage.toString(message);
			bw.write(tosend+'\n');
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	 
	 
	/**
	 * 
	 */	
	public void upgradeSSL(){
		
		System.setProperty("javax.net.ssl.trustStore", "c:/serveur/cacerts");
		System.setProperty("javax.net.ssl.trustStorePassword", "changeit");		
		System.setProperty("javax.net.ssl.keyStore", "c:/serveur/mykeystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		SSLSocketFactory sslSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
	    try {
			sslsocket = (SSLSocket)sslSocketFactory.createSocket(currentsocket, currentsocket.getInetAddress().getHostAddress(),currentsocket.getPort(),false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    sslsocket.setUseClientMode(false);	    
	    currentsocket = sslsocket;	    		
	    setComm(currentsocket);
	    sslup = true;
	}
	
	
	public void downSSL(){
		currentsocket = socket;
		setComm(currentsocket);
		try {
			sslsocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sslup = false;
	}
	
	
	
	/**
	 * 
	 */
	public void setComm(Socket currentsocket){
		try {
			bw = new BufferedWriter(new OutputStreamWriter(currentsocket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(currentsocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		this.connect = false;
	}
	
	public void deconnex(){
		try {
			this.bw.close();
			this.br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.connect = false;
	}
		
	
	
}
