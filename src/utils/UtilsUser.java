/**
 * 
 */
package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.User;

/**
 * @author Lwaxana
 *
 */
public class UtilsUser {

public static void setUserOnline(User user){
		
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			user.setOnline(true);
			session.saveOrUpdate(user);
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} finally {
		    session.flush();
		    session.close();
		   
		}	
	}

public static void setUserOffline(User user){
	Session session = UtilsHibernate.instance().openSession();
	Transaction tx = null;
	try{
		tx = session.beginTransaction(); 
		user.setOnline(false);
		session.saveOrUpdate(user);
		tx.commit(); 

	}
	catch (HibernateException e) {
		if (tx != null) tx.rollback(); 
		e.printStackTrace(); 
	}finally {
	    session.flush();
	    session.close();
	   
	}		
}
	
	public static void miseAjourUser(User user){
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			session.update(user);
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} finally {
		    session.flush();
		    session.close();
		   
		}			
	}
	
	public static void SetNick(User user, String str){
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			user.setNick(str);
			session.update(user);
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} finally {
		    session.flush();
		    session.close();
		   
		}			
	}
}


