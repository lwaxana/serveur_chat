package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Serveur;
import utils.UtilsHibernate;
import utils.UtilsReseau;

public class InsertServeur {

	public static void main(String[] args) {
		
		Session session = null;
		session = UtilsHibernate.instance().openSession();
		
				
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); //commencer la transaction
			Serveur serv = new Serveur();
			serv.setIP(UtilsReseau.getLocalIp());
			serv.setPort(2009);
			serv.setNom("Ginette4000");
			serv.setOnline(false);
			session.save(serv);
									
			tx.commit(); // Mettre physiquement les objets cr��s dans la base de donn�es
			System.out.println("pas de probl�me de mapping");
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); // On lui dit de ne pas tenir compte des op�rations effectu�es
			e.printStackTrace(); 
		}
		
		

	}

}
