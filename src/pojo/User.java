package pojo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Set;

import javax.net.ssl.SSLSocket;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import message.Message;
import serv.GestionnaireChannel;
import typemessage.TypeMessage;
import utils.UtilsMessage;
import utils.UtilsUser;

@Entity
@Table(name ="User", catalog = "chat")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Runnable {

	protected Integer iduser;
	protected  String prenom;
	protected  String nom;
	protected  String email;
	protected  String password;
	protected  String nick;
	protected  boolean ban;
	protected  Adresse adresse;
	protected Set<Activiteserveur> activiteserveur;
	protected Set<Activitesalon> activitesalon;
	protected Set<Salon> salons;
	protected Socket socket;
	protected GestionnaireChannel gestionnaireChannel;
	protected SSLSocket sslSocket;
	protected Socket currentSocket;
	protected BufferedReader br;
	protected BufferedWriter bw;
	protected boolean connect;
	protected Message message;
	protected boolean online;
		
	
	public User(){
		
	}
					
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iduser", nullable = false, unique = true)
	public Integer getIduser() {
		return iduser;
	}
	
	@Column(name = "prenom", nullable = false, length = 50)
	public String getPrenom() {
		return prenom;
	}
	
	@Column(name ="nom", nullable = false, length = 100)
	public String getNom() {
		return nom;
	}
	
	@Column(name = "email", nullable = false, unique = true, length = 100)
	public String getEmail() {
		return email;
	}
	
	@Column(name = "password", nullable = false, length = 64)
	public String getPassword() {
		return password;
	}
	
	@Column(name = "nick", nullable = false, length = 20)
	public String getNick() {
		return nick;
	}
	
	@Column(name = "ban", nullable = false)
	public boolean isBan() {
		return ban;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "idadresse", nullable = false)
	public Adresse getAdresse() {
		return adresse;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Salon> getSalons() {
		return salons;
	}
	
	@Column(name = "online", nullable = false)	
	public boolean isOnline() {
		return online;
	}
	
	
	/**
	 * @return the activiteserveur
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Activiteserveur> getActiviteserveur() {
		return activiteserveur;
	}

	/**
	 * @return the activitesalon
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Activitesalon> getActivitesalon() {
		return activitesalon;
	}

	/**
	 * @param activiteserveur the activiteserveur to set
	 */
	public void setActiviteserveur(Set<Activiteserveur> activiteserveur) {
		this.activiteserveur = activiteserveur;
	}

	/**
	 * @param activitesalon the activitesalon to set
	 */
	public void setActivitesalon(Set<Activitesalon> activitesalon) {
		this.activitesalon = activitesalon;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public void setSalons(Set<Salon> salons) {
		this.salons = salons;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setBan(boolean ban) {
		this.ban = ban;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", prenom=" + prenom + ", nom=" + nom
				+ ", email=" + email + ", password=" + password + ", nick="
				+ nick + ", ban=" + ban + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	
	@Override
	public void run() {
		
		gestionnaireChannel = GestionnaireChannel.getInstance();
		UtilsUser.setUserOnline(this);
		this.setNick("defaut");
		gestionnaireChannel.ajouterUser(this);
		connect = true;
		setComm();
		String string;
		envoyerListeChannels();
		envoyerInfoServeur();
		while ( connect ){
			try {
				string = br.readLine();
				message = (Message)UtilsMessage.fromString(string);
				traiterMessage(message);
			} catch (IOException e) {
				deconnex();
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	public void setSocket(Socket socket){
		this.socket = socket;
		this.currentSocket = socket;
	}
	
	public void setComm(){
		try {
			bw = new BufferedWriter(new OutputStreamWriter(currentSocket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void traiterMessage(Message message){
		TypeMessage typemessage = message.getTypeMessage();
		
		if (typemessage == TypeMessage.LeaveChannel){
			gestionnaireChannel.retirerUserSalon(gestionnaireChannel.getSalon(message.getNomsalon()), this);
		}
		/*
		if ( typemessage == TypeMessage.MessageChannel){
			Salon s = gestionnaireChannel.getSalon(message.getNomsalon());
			s.envoyerMessage(message);
		}
		*/
		if ( typemessage == TypeMessage.ServerInfo){
			envoyerInfoServeur();
		}
		if ( typemessage == TypeMessage.ServerInfoChannel ){
			envoyerListeChannels();
		}
		if ( typemessage == TypeMessage.ConnexionStop){
			String[] salons = gestionnaireChannel.getChannelList();
			for ( String s : salons){
				Salon sal = gestionnaireChannel.getSalon(s);
				if ( sal.contientUser(this)){
					sal.retirerUser(this);
				}
			}
			gestionnaireChannel.retirerUserGestionnaire(this);
			UtilsUser.setUserOffline(this);
			deconnex();
		}
		if ( typemessage == TypeMessage.JoinChannel ){
			if ( gestionnaireChannel.ajouterUserSalon(message.getNomsalon(), this) ){
				String str = message.getNomsalon();
				message = new Message();
				message.setTypeMessage(TypeMessage.JoinChannelOk);
				message.setNomsalon(str);
				envoyerMessageClient(message); 
				message = new Message();
				message.setTypeMessage(TypeMessage.ChannelUserList);
				message.setNomsalon(str);
				message.setUsers( gestionnaireChannel.getSalon(str).getListUsers() );
				envoyerMessageClient(message);
			}
			else{
				//MSG ERREUR DEJA SUR CHANNEL
			}		
			
		}
		if ( typemessage == TypeMessage.MessageChannel){
			Salon s = gestionnaireChannel.getSalon(message.getNomsalon());
			StringBuffer sbf = new StringBuffer();
			sbf.append(this.getNick());
			sbf.append(" > ");
			sbf.append(message.getMessage());
			message = new Message();
			message.setMessage(sbf.toString());
			message.setNomsalon(s.getNom());
			message.setTypeMessage(TypeMessage.MessageChannel);
			s.envoyerMessage(message);			
		}
		if ( typemessage == TypeMessage.ChangerNick){
			String oldnick = this.getNick();
			if ( gestionnaireChannel.checkNick(message.getNick())){
				envoyerNickDejaPris();
			}
			else{
				this.nick = message.getNick();
				UtilsUser.SetNick(this, message.getNick());
				message = new Message();
				message.setTypeMessage(TypeMessage.NickOk);
				message.setNick(this.nick);
				envoyerMessageClient(message);
				message = new Message();
				gestionnaireChannel.changementNick(this, oldnick+" has changed his nick for "+nick);
				
			}
		}
		
		if ( typemessage == TypeMessage.LeaveChannel){
			gestionnaireChannel.retirerUserSalon(gestionnaireChannel.getSalon(message.getNomsalon()), this);			
		}
				
	}
	
	public synchronized void envoyerMessageClient(Message message){
		try {
			String tosend = UtilsMessage.toString(message);
			bw.write(tosend+'\n');
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void envoyerListeChannels(){
		message = new Message();
		message.setTypeMessage(TypeMessage.ServerInfoChannel);
		message.setInfos(gestionnaireChannel.getChannelList());
		envoyerMessageClient(message);				
	}
	
	public void envoyerInfoServeur(){
		message = new Message();
		message.setTypeMessage(TypeMessage.ServerInfo);
		message.setInfos(gestionnaireChannel.getServeurInfo());
		envoyerMessageClient(message);		
	}
	
	public void deconnex(){
		try {
			this.br.close();
			this.bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect = false;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	public void envoyerNickDejaPris(){
		message = new Message();
		message.setTypeMessage(TypeMessage.NickUsed);
		envoyerMessageClient(message);		
	}
	
	
	

}
