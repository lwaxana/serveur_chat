/**
 * 
 */
package test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pojo.Moderateur;
import pojo.Salon;
import pojo.Serveur;
import utils.UtilsHibernate;
import utils.UtilsReseau;

/**
 * @author Lwaxana
 *
 */
public class Test5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Serveur> listserveur = null;
		Set<Salon> listeSalon = new TreeSet<>();
		Serveur serveur = null;
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			/*
			serveur  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("IP", UtilsReseau.getLocalIp())).uniqueResult();
			listeSalon = serveur.getSalons();
			*/
			Salon salon = (Salon) session.createCriteria(Salon.class).add(Restrictions.eq("nom", "Newbie")).uniqueResult();
			System.out.println(salon);
			Set<Moderateur> modos = salon.getModerateurs();
			System.out.println(modos);
			
			
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		}
		
		for ( Salon s : listeSalon){
			System.out.println(s.getNom()+ " "+s.getIdsalon()+" "+ s.getTypesalon().getType());
		}
		
		
		

	}

}
