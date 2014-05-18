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
 	
    private Set<Salon> salonsModeration;
    
    public Moderateur(){
    	
    }

    /**
	 * @return the salonsModeration
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="modere", catalog="chat", joinColumns = {
				@JoinColumn(name="idmoderateur", nullable=false, updatable=false) }, inverseJoinColumns = {
			@JoinColumn(name="idsalon", nullable=false, updatable=false) })
	
	public Set<Salon> getSalonsModeration() {
		return salonsModeration;
	}


	/**
	 * @param salonsModeration the salonsModeration to set
	 */
	public void setSalonsModeration(Set<Salon> salonsModeration) {
		this.salonsModeration = salonsModeration;
	}
    
    

}
