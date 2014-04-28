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
import utils.UtilsMessage;
import utils.UtilsUser;

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
	
	public ClientHandler(Socket socket){
		this.socket = socket;
		this.currentsocket = socket;
		connect = true;
		auth = false;
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
				if ( string != null){
					message = (Message)UtilsMessage.fromString(string);
					if ( message.getTypeMessage().equals(TypeMessage.Authentification)){
						user = UtilsUser.checkUser(message.getEmail());
						
						if ( user != null){
							if ( user.getEmail().equalsIgnoreCase(message.getEmail()) && user.getPassword().equalsIgnoreCase(message.getPassword())){
								System.out.println("login ok");
								message = new Message();
								message.setTypeMessage(TypeMessage.Authentificationok);
								envoyerMessage(message);
							}
							else{
								message = new Message();
								message.setTypeMessage(TypeMessage.AuthentificationFail);
								message.setMessage("Mauvais login et mdp");
								envoyerMessage(message);
							}
						}
						else{
							message = new Message();
							message.setTypeMessage(TypeMessage.AuthentificationFail);
							message.setMessage("L utilisateur n existe pas");
							envoyerMessage(message);
						}
					}
					if ( message.getTypeMessage().equals(TypeMessage.NewUser)){
						
					}
					if (message.getTypeMessage().equals(TypeMessage.ConnexionStart)){
						message = new Message();
						message.setTypeMessage(TypeMessage.ConnexionOk);
						envoyerMessage(message);						
					}
					if ( message.getTypeMessage().equals(TypeMessage.AuthentificationStart)){
						
						message = new Message();
						message.setTypeMessage(TypeMessage.StartSSL);
						envoyerMessage(message);
						upgradeSSL();
					}
					if ( message.getTypeMessage().equals(TypeMessage.NewUserStart)){
						
						message = new Message();
						message.setTypeMessage(TypeMessage.StartSSL);
						envoyerMessage(message);
						upgradeSSL();
					}
										
				}

			} catch (ClassNotFoundException | IOException e) {
				this.connect = false;
				e.printStackTrace();
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
			System.out.println(tosend);
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
	    System.out.println("server sslsocket"+sslsocket.getLocalPort());
	    System.out.println("serveur sslsoclet"+sslsocket.getPort());
	    setComm(currentsocket);
	}
	
	/**
	 * 
	 */
	public void downSSL(){
		currentsocket = socket;
		try {
			sslsocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setComm(currentsocket);
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
	
	
		
	
	
}
