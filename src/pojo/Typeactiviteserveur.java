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
@Table(name = "typeactiviteserveur", catalog = "chat")
public class Typeactiviteserveur {

	private Integer idtype;
	private String type;
	private Set<Activiteserveur> activiteserveur;

	public Typeactiviteserveur(){
		
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
	@Column(name = "type", nullable = false, length = 50)
	public String getType() {
		return type;
	}

	/**
	 * @return the activiteserveur
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "typeactivite")	
	public Set<Activiteserveur> getActiviteserveur() {
		return activiteserveur;
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
	 * @param activiteserveur the activiteserveur to set
	 */
	public void setActiviteserveur(Set<Activiteserveur> activiteserveur) {
		this.activiteserveur = activiteserveur;
	}
	
	
}
