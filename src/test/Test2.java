package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Moderateur;
import pojo.Pays;
import pojo.Salon;
import pojo.Serveur;
import pojo.User;
import utils.UtilsHibernate;

public class Test2 {

	public static void main(String[] args) {
		
		Session session = null;
		session = UtilsHibernate.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
			Pays pays = new Pays();
			pays.setNompays("Belgique");
			
			session.persist(pays);
			
			Adresse adresse = new Adresse();
			adresse.setNumero("69");
			adresse.setRue("Rue que c'est pas bien");
			adresse.setVille("Wasmes Plage");
			adresse.setZip("7500");
			adresse.setPays(pays);
			
			session.persist(adresse);
			
			User user = new User();
			user.setBan(false);
			user.setEmail("phil@phil.com");
			user.setNick("lwaxana");
			user.setNom("Jankowski");
			user.setPrenom("Phil");
			user.setPassword("4B25923A4F31B83195CEB0D160E0A1A6D1556B5A2CD4C582FF40AA0498AE1578");
			user.setAdresse(adresse);
			
			session.persist(user);
		
			
			tx.commit(); // Mettre physiquement les objets créés dans la base de données
			System.out.println("pas de problème de mapping");
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); // On lui dit de ne pas tenir compte des opérations effectuées
			e.printStackTrace(); 
		}
		finally {
				
				
				session.close(); 
			
			
		}
		

	}

}
