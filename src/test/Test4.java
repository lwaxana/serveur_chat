package test;

import java.util.Iterator;
import java.util.List;











import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Adresse;
import pojo.Moderateur;
import pojo.Pays;
import pojo.Salon;
import pojo.Serveur;
import pojo.User;
import utils.UtilsHibernate;
import utils.UtilsPassword;

public class Test4 {

	public static void main(String[] args) {
		
		Session session = null;
		session = UtilsHibernate.instance().openSession();
		Serveur serv;
				
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			/*
			Criteria cr = session.createCriteria(Serveur.class);
			cr.add(Restrictions.eq("nom", "Login"));
			results = cr.list();
			*/
			/*
			if ( results == null ){
				System.out.println("pas de resultat");
			}
			else {
				it = results.iterator();
				while ( it.hasNext() ){
					serv = (Serveur)it.next();
					System.out.println(serv.getNom());
					System.out.println(serv.getIdserveur());
					System.out.println(serv.getIP());
					}
				}
			
			*/
			
			serv  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("nom", "Loin")).uniqueResult();
									
			tx.commit(); // Mettre physiquement les objets créés dans la base de données
			System.out.println("pas de problème de mapping");
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); // On lui dit de ne pas tenir compte des opérations effectuées
			e.printStackTrace(); 
		}
		
		

	}

}
