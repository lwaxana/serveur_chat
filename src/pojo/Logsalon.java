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
@Table(name = "logsalon", catalog = "chat")
public class Logsalon {
	
	private Integer idlog;
	private Date date;
	private String log;
	private Salon salon;

	public Logsalon(){
		
	}

	/**
	 * @return the idlog
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idlog", nullable = false, unique = true)
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
	 * @return the salon
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idsalon", nullable = false)
	public Salon getSalon() {
		return salon;
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
	 * @param salon the salon to set
	 */
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	
	
	
}
