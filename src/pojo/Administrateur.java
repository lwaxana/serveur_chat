package pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "administrateur", catalog = "chat")
@PrimaryKeyJoinColumn(name = "idadministrateur", referencedColumnName = "iduser" )
public class Administrateur extends User {
  
	private Set<Serveur> serveurs = new HashSet<Serveur>(0);
	
	public Administrateur(){
		
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="gere", catalog="chat", joinColumns = {
			@JoinColumn(name="idadministrateur", nullable=false, updatable=false) }, inverseJoinColumns = {
			@JoinColumn(name="idserveur", nullable=false, updatable=false) })
	public Set<Serveur> getServeurs() {
		return serveurs;
	}

	public void setServeurs(Set<Serveur> serveurs) {
		this.serveurs = serveurs;
	}
	
	
	
	

}
