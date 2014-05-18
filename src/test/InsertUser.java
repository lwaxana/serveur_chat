package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Adresse;
import pojo.Pays;
import pojo.Salon;
import pojo.Typesalon;
import pojo.User;
import utils.UtilsHibernate;

public class InsertUser {

	public static void main(String[] args) {
		
		Session session = null;
		session = UtilsHibernate.instance().openSession();
		Pays pays = null;
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
			
			pays  = (Pays) session.createCriteria(Pays.class).add(Restrictions.eq("nompays", "Belgique")).uniqueResult();
			
			
			
			Adresse adresse = new Adresse();
			adresse.setNumero("69");
			adresse.setRue("Rue que c'est pas bien");
			adresse.setVille("Wasmes Plage");
			adresse.setZip("7500");
			adresse.setPays(pays);
			
			session.persist(adresse);
			
			User user = new User();
			user.setBan(false);
			user.setEmail("moderateur");
			user.setNick("lwaxana");
			user.setNom("Jankowski");
			user.setPrenom("Phil");
			user.setPassword("4B25923A4F31B83195CEB0D160E0A1A6D1556B5A2CD4C582FF40AA0498AE1578");
			user.setAdresse(adresse);
			
			session.persist(user);
		
			
			
			
			tx.commit(); // Mettre physiquement les objets cr��s dans la base de donn�es
			
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); // On lui dit de ne pas tenir compte des op�rations effectu�es
			e.printStackTrace(); 
		}
		finally {
				
				
				session.close(); 
			
			
		}
		

	}

}
