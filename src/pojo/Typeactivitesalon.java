package pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "typeactivitesalon", catalog = "chat")
public class Typeactivitesalon {
	
	private Integer idtype;
	private String type;
	private Set<Activitesalon> activitesalon;
	
	public Typeactivitesalon(){
		
	}

	/**
	 * @return the idtype
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtype", nullable = false, unique = true)
	public Integer getIdtype() {
		return idtype;
	}

	/**
	 * @return the type
	 */
	@Column(name = "type", nullable = false, length = 100)
	public String getType() {
		return type;
	}

	/**
	 * @return the activitesalon
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeactivite")
	public Set<Activitesalon> getActivitesalon() {
		return activitesalon;
	}

	/**
	 * @param idtype the idtype to set
	 */
	public void setIdtype(Integer idtype) {
		this.idtype = idtype;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param activitesalon the activitesalon to set
	 */
	public void setActivitesalon(Set<Activitesalon> activitesalon) {
		this.activitesalon = activitesalon;
	}
	
	
	

}
