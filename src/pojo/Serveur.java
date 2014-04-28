package pojo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import serv.ClientHandler;

@Entity
@Table(name = "serveur", catalog = "chat")
public class Serveur {

	private Integer idserveur;
	private String nom;
	private String IP;
	private int port;
	private String password;
	private boolean online;
	
	private Set<Activiteserveur> activiteserveur;
	private Set<Logserveur> log;
	private Set<Salon> salons ;
		
	private ServerSocket serversocket;
	
	public Serveur(){
		
	}
			
	/**
	 * @return the idserveur
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idserveur", nullable = false, unique = true)
	public Integer getIdserveur() {
		return idserveur;
	}

	/**
	 * @return the nom
	 */
	@Column(name = "nom", nullable = false, length = 20)
	public String getNom() {
		return nom;
	}

	/**
	 * @return the iP
	 */
	@Column(name = "IP", nullable = false, length = 15)
	public String getIP() {
		return IP;
	}

	/**
	 * @return the port
	 */
	@Column(name = "port", nullable = false)
	public int getPort() {
		return port;
	}

	/**
	 * @return the password
	 */
	@Column(name = "password", nullable = true, length = 64)
	public String getPassword() {
		return password;
	}

	/**
	 * @return the online
	 */
	@Column(name = "online", nullable = false, length = 1)
	public boolean isOnline() {
		return online;
	}

	/**
	 * @return the activiteserveur
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serveur")
	public Set<Activiteserveur> getActiviteserveur() {
		return activiteserveur;
	}

	/**
	 * @return the log
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serveur")
	public Set<Logserveur> getLog() {
		return log;
	}

	/**
	 * @return the salons
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "serveur")
	public Set<Salon> getSalons() {
		return salons;
	}

	/**
	 * @param idserveur the idserveur to set
	 */
	public void setIdserveur(Integer idserveur) {
		this.idserveur = idserveur;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param iP the iP to set
	 */
	public void setIP(String iP) {
		IP = iP;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

	/**
	 * @param activiteserveur the activiteserveur to set
	 */
	public void setActiviteserveur(Set<Activiteserveur> activiteserveur) {
		this.activiteserveur = activiteserveur;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Set<Logserveur> log) {
		this.log = log;
	}

	/**
	 * @param salons the salons to set
	 */
	public void setSalons(Set<Salon> salons) {
		this.salons = salons;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Serveur [idserveur=" + idserveur + ", nom=" + nom + ", IP="
				+ IP + ", port=" + port + ", password=" + password
				+ ", online=" + online + "]";
	}

	
	public void demarrer() {
		try {
			serversocket = new ServerSocket(2009);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setOnline(true);
		while (online){
			try {
				Thread t = new Thread(new ClientHandler(serversocket.accept()));
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void close(){
		try {
			System.out.println(online);
			serversocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
