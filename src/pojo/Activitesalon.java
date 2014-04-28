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
@Table(name = "activitesalon", catalog = "chat")
public class Activitesalon {

	private Integer idactivite;
	private Date heure;
	private String activite;
	private Salon salon;
	private Typeactivitesalon typeactivite;
	
	public Activitesalon(){
		
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
	 * @return the activite
	 */
	@Column(name = "activite", nullable = false, length = 100 )
	public String getActivite() {
		return activite;
	}

	/**
	 * @return the salon
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsalon", nullable = false)
	public Salon getSalon() {
		return salon;
	}

	/**
	 * @return the typeactivite
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idtype", nullable = false)
	public Typeactivitesalon getTypeactivite() {
		return typeactivite;
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
	 * @param activite the activite to set
	 */
	public void setActivite(String activite) {
		this.activite = activite;
	}

	/**
	 * @param salon the salon to set
	 */
	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	/**
	 * @param typeactivite the typeactivite to set
	 */
	public void setTypeactivite(Typeactivitesalon typeactivite) {
		this.typeactivite = typeactivite;
	}
	
	

}
