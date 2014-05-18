package pojo;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import message.Message;
import message.Utilisateur;
import serv.GestionnaireChannel;
import typemessage.TypeMessage;

@Entity
@Table(name = "salon", catalog = "chat")
public class Salon {

	private int idsalon;
	private String nom;
	private String password;
	private Typesalon typesalon;
	private Serveur serveur;
	private User user;
	private Set<Moderateur> moderateurs;
	private Set<Activiteserveur> activiteserveur;
	private Set<Logsalon> log;
	private Set<Activitesalon> activitesalon;
	
	
	@Transient
	private GestionnaireChannel gestionchannel;
	@Transient
	//private List<User> users = new ArrayList<>();
	private CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
	
	public Salon(){
	}
	
	
	
	/**
	 * @return the idsalon
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsalon", unique = true, nullable = false)
	public int getIdsalon() {
		return idsalon;
	}
	/**
	 * @return the nom
	 */
	@Column(name = "nom", nullable = false, length = 50)
	public String getNom() {
		return nom;
	}
	/**
	 * @return the password
	 */
	@Column(name = "password", nullable = true, length = 64 )
	public String getPassword() {
		return password;
	}
	/**
	 * @return the typesalon
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idtype", nullable = false)
	public Typesalon getTypesalon() {
		return typesalon;
	}
	/**
	 * @return the serveur
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idserveur", nullable = false)
	public Serveur getServeur() {
		return serveur;
	}
		
	/**
	 * @return the moderateurs
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="modere", catalog="chat", joinColumns = {
			@JoinColumn(name="idsalon", nullable=false, updatable=false) }, inverseJoinColumns = {
			@JoinColumn(name="idmoderateur", nullable=false, updatable=false) })
	public Set<Moderateur> getModerateurs() {
		return moderateurs;
	}
	/**
	 * @return the activiteserveur
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salon")
	public Set<Activiteserveur> getActiviteserveur() {
		return activiteserveur;
	}
	/**
	 * @return the log
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salon")
	public Set<Logsalon> getLog() {
		return log;
	}
	/**
	 * @return the activitesalon
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "salon")
	public Set<Activitesalon> getActivitesalon() {
		return activitesalon;
	}
		
	
	/**
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iduser", nullable = true)
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * @param idsalon the idsalon to set
	 */
	public void setIdsalon(int idsalon) {
		this.idsalon = idsalon;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param typesalon the typesalon to set
	 */
	public void setTypesalon(Typesalon typesalon) {
		this.typesalon = typesalon;
	}
	/**
	 * @param serveur the serveur to set
	 */
	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}
	
	/**
	 * @param moderateurs the moderateurs to set
	 */
	public void setModerateurs(Set<Moderateur> moderateurs) {
		this.moderateurs = moderateurs;
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
	public void setLog(Set<Logsalon> log) {
		this.log = log;
	}
	/**
	 * @param activitesalon the activitesalon to set
	 */
	public void setActivitesalon(Set<Activitesalon> activitesalon) {
		this.activitesalon = activitesalon;
	}

	@Transient
	public boolean ajouterUser(User user){
		if ( !users.contains(user)){
			
			String str = user.getNick();
			Message message = new Message();
			message.setTypeMessage(TypeMessage.MessageChannel);
			message.setNomsalon(this.getNom());
			message.setMessage(str +" a rejoint le channel");
			envoyerMessage(message);
			moderateurs = getModerateurs();
			message = new Message();
			message.setTypeMessage(TypeMessage.ChannelAddUser);
			Utilisateur[] liste = new Utilisateur[1];
			if ( user instanceof User){
				liste[0]  = new Utilisateur(str, 1);				
			}
			if ( user instanceof Moderateur){
			
				boolean modo = false;
				for ( Moderateur m : moderateurs){
					if ( user.getIduser() == m.getIduser()){
						liste[0]  = new Utilisateur(str, 2);
						modo = true;
					}
				}
				if ( !modo){
					liste[0]  = new Utilisateur(str, 1);
				}
				
			}
			if ( user instanceof Administrateur){
				liste[0]  = new Utilisateur(str, 3);	
			}
			message.setUsers(liste);
			message.setNomsalon(this.getNom());
			envoyerMessage(message);
			users.add(user);
			
			
			return true;
		}
		else{
			return false;
		}
		
	}
	
	@Transient
	public void retirerUser(User user){
		for ( User u : users){
			if ( u.getNick().equalsIgnoreCase(user.getNick())){
				String str = user.getNick();
				users.remove(u);
				Message message = new Message();
				message.setTypeMessage(TypeMessage.MessageChannel);
				message.setNomsalon(this.getNom());
				message.setMessage(str+" a quitté le channel");
				envoyerMessage(message);	
				message = new Message();
				message.setTypeMessage(TypeMessage.ChannelLeaveUser);
				message.setNomsalon(this.getNom());
				Utilisateur[] liste = new Utilisateur[1];
				liste[0] = new Utilisateur(user.getNick(), 0);
				message.setUsers(liste);
				envoyerMessage(message);
				
			}
		}
	}
	
	@Transient
	public Utilisateur[] getListUsers(){
		moderateurs = getModerateurs();
		Utilisateur[] liste;
		liste = new Utilisateur[users.size()];
		int i = 0;
		for ( User u : users){
			
			if ( u instanceof User){
				liste[i]  = new Utilisateur(u.getNick(), 1);				
			}
			if ( u instanceof Moderateur){
				boolean modo = false;
				for ( Moderateur m : moderateurs){
					if ( u.getIduser() == m.getIduser()){
						liste[0]  = new Utilisateur(u.getNick(), 2);
						modo = true;
					}
				}
				if ( !modo){
					liste[0]  = new Utilisateur(u.getNick(), 1);
				}
				modo = false;
			}
			if ( u instanceof Administrateur){
				liste[i]  = new Utilisateur(u.getNick(), 3);	
			}
			i++;
		}
		return liste;
	}
	
	@Transient
	public void envoyerMessage(Message message){
		for ( User u : users){
			u.envoyerMessageClient(message);
		}
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Salon [idsalon=" + idsalon + ", nom=" + nom + ", password="
				+ password + ", typesalon=" + typesalon + ", serveur="
				+ serveur + ", users=" + users + "]";
	}
	
	public boolean contientUser(User u){
		if ( users.contains(u)){
			return true;
		}
		else{
			return false;
		}
	}
	
}
