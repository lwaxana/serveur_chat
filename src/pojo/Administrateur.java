package pojo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "administrateur", catalog = "chat")
@PrimaryKeyJoinColumn(name = "idadministrateur", referencedColumnName = "iduser" )
public class Administrateur extends User {
  
	
	public Administrateur(){
		
	}

}
