package pojo;

import java.net.Socket;
import java.util.Set;

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
import javax.persistence.Table;
import javax.persistence.Transient;

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
	protected Socket socket;
	
	
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
	@Transient
	@Override
	public void run() {
		
		
	}

	@Transient
	public void setSocket(Socket socket){
		this.socket = socket;
	}

}
