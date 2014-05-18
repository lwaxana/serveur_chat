package pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "activiteserveur", catalog = "chat")
public class Activiteserveur {

	private Integer idactivite;
	private Date heure;
	private String description;
	private Typeactiviteserveur typeactivite;
	private Serveur serveur;
	private User user;
	private Salon salon;
	
	public Activiteserveur(){
		
	}

	/**
	 * @return the idactivite
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idactivite", nullable = false, unique = true)
	public Integer getIdactivite() {
		return idactivite;
	}

	/**
	 * @return the heure
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "heure", nullable = false)
	public Date getHeure() {
		return heure;
	}

	/**
	 * @return the description
	 */
	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return description;
	}

	/**
	 * @return the typeactivite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idtype", nullable = false)
	public Typeactiviteserveur getTypeactivite() {
		return typeactivite;
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
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "iduser", nullable = true)
	public User getUser() {
		return user;
	}

	/**
	 * @return the salon
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsalon", nullable = true)
	public Salon getSalon() {
		return salon;
	}
	
	

	/**
	 * @param idactivite the idactivite to set
	 */
	public void setIdactivite(Integer idactivite) {
		this.idactivite = idactivite;
	}

	/**
	 * @param heure the heure to set
	 */
	public void setHeure(Date heure) {
		this.heure = heure;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param typeactivite the typeactivite to set
	 */
	public void setTypeactivite(Typeactiviteserveur typeactivite) {
		this.typeactivite = typeactivite;
	}

	/**
	 * @param serveur the serveur to set
	 */
	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @param salon the salon to set
	 */
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	
	

}
