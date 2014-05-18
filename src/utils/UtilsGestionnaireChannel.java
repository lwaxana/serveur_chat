/**
 * 
 */
package utils;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Salon;
import pojo.Serveur;



/**
 * @author Lwaxana
 *
 */
public class UtilsGestionnaireChannel {

	/**
	 * 
	 * @return
	 */
	public static Serveur getServeur(){
		Serveur serveur = null;
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			serveur  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("IP", UtilsReseau.getLocalIp())).uniqueResult();
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		}

		return serveur;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Set<Salon> getSalon(){
		Set<Salon> listeSalon = null;
		Serveur serveur = null;
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			serveur  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("IP", UtilsReseau.getLocalIp())).uniqueResult();
			listeSalon = serveur.getSalons();
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		}
		return listeSalon;
	}
	
	/**
	 * 
	 */
	public static void ajouterSalon(Salon salon){
		
	}
}
