package pojo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "moderateur", catalog = "chat")
@PrimaryKeyJoinColumn(name = "idmoderateur", referencedColumnName = "iduser" )
public class Moderateur extends User {
 	
    private Set<Salon> salons;
    
    public Moderateur(){
    	
    }

	
	/**
	 * @return the salons
	 */
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="modere", catalog="chat", joinColumns = {
			@JoinColumn(name="idmoderateur", nullable=false, updatable=false) }, inverseJoinColumns = {
			@JoinColumn(name="idsalon", nullable=false, updatable=false) })
	public Set<Salon> getSalons() {
		return salons;
	}

	/**
	 * @param salons the salons to set
	 */
	public void setSalons(Set<Salon> salons) {
		this.salons = salons;
	}
    
    

}
