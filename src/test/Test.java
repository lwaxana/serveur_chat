package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Adresse;
import pojo.Moderateur;
import pojo.Pays;
import pojo.Serveur;
import pojo.User;
import utils.UtilsHibernate;

public class Test {

	public static void main(String[] args) {
		
		Session session = null;
		session = UtilsHibernate.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			
		
									
			tx.commit(); // Mettre physiquement les objets cr��s dans la base de donn�es
			System.out.println("pas de probl�me de mapping");
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
