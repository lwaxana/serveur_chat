package pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "adresse", catalog = "chat")
public class Adresse {

	private Integer idadresse;
	private String rue;
	private String numero;
	private String ville;
	private String zip;
	private Set<User> users;
	private Pays pays;

	public Adresse(){}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idadresse", unique = true, nullable = false)
	public Integer getIdadresse() {
		return idadresse;
	}
	
	@Column(name = "rue", nullable = false, length = 100)
	public String getRue() {
		return rue;
	}

	@Column(name = "numero", nullable = false, length = 10)
	public String getNumero() {
		return numero;
	}

	@Column(name ="ville", nullable = false, length = 50)
	public String getVille() {
		return ville;
	}
	
	@Column(name = "zip", nullable = false, length = 10 )
	public String getZip() {
		return zip;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idpays", nullable = false)
	public Pays getPays() {
		return pays;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "adresse")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void setIdadresse(Integer idadresse) {
		this.idadresse = idadresse;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}




}
