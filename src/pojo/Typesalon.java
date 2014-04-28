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
@Table(name = "typesalon", catalog = "chat")
public class Typesalon {
	
	private Integer idtype;
	private String type;
	private Set<Salon> salon;
	
	public Typesalon(){
		
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
	@Column(name = "type", nullable = false, length = 20 )
	public String getType() {
		return type;
	}

	/**
	 * @return the salon
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typesalon")
	public Set<Salon> getSalon() {
		return salon;
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
	 * @param salon the salon to set
	 */
	public void setSalon(Set<Salon> salon) {
		this.salon = salon;
	}
	
	

}
