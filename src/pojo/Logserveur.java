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
@Table(name = "logserveur", catalog = "chat")
public class Logserveur {

	private Integer idlog;
	private Date date;
	private String log;
	private Serveur serveur;

	public Logserveur(){
		
	}

	/**
	 * @return the idlog
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlog", unique = true, nullable = false)
	public Integer getIdlog() {
		return idlog;
	}

	/**
	 * @return the date
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	/**
	 * @return the log
	 */
	@Column(name = "log", nullable = false, length = 100)
	public String getLog() {
		return log;
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
	 * @param idlog the idlog to set
	 */
	public void setIdlog(Integer idlog) {
		this.idlog = idlog;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	
	/**
	 * @param serveur the serveur to set
	 */
	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}
	
	
}
