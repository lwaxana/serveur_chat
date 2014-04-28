package pojo;

import java.util.List;
import java.util.Set;

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

@Entity
@Table(name = "salon", catalog = "chat")
public class Salon {

	private int idsalon;
	private String nom;
	private String password;
	private Typesalon typesalon;
	private Serveur serveur;
	//private GestionSalon gestionsalon;
	private List<User> users;
	private Set<Moderateur> moderateurs;
	private Set<Activiteserveur> activiteserveur;
	private Set<Logsalon> log;
	private Set<Activitesalon> activitesalon;
	
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
	 * @return the users
	 */
	@Transient
	public List<User> getUsers() {
		return users;
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
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
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

	
}
