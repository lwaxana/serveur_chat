/**
 * 
 */
package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Salon;
import pojo.Serveur;
import pojo.Typesalon;
import utils.UtilsHibernate;
import utils.UtilsReseau;
import ihm.Fenetre2;

/**
 * @author Lwaxana
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Fenetre2 fen = new Fenetre2();
		
		String nom = "Ginette4000";
		int port = 2009;
		Serveur serv = null;
		
		Session session = null;
		session = UtilsHibernate.instance().openSession();
		
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			serv = (Serveur)session.createCriteria(Serveur.class).add(Restrictions.eq("nom", nom)).uniqueResult();
											
					
			tx.commit(); 
			
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		}
		finally {
								
				session.close(); 
			
			
		}
		
		
		serv.demarrer();
		
	}

}
