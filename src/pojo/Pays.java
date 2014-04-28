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
@Table(name = "pays", catalog = "chat")
public class Pays {

	private Integer idpays;
	private String nompays;
	private Set<Adresse> adresses;
	
	public Pays(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpays", nullable = false, unique = true)
	public Integer getIdpays() {
		return idpays;
	}
	
	@Column(name = "nompays", nullable = false, length = 50)
	public String getNompays() {
		return nompays;
	}

	public void setIdpays(Integer idpays) {
		this.idpays = idpays;
	}

	public void setNompays(String nompays) {
		this.nompays = nompays;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pays")
	public Set<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(Set<Adresse> adresses) {
		this.adresses = adresses;
	}
	
	

}
