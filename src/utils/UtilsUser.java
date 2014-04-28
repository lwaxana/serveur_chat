/**
 * 
 */
package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Serveur;
import pojo.User;

/**
 * @author Lwaxana
 *
 */
public class UtilsUser {

	public static User checkUser(String login){
		User user = null;
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			user  = (User) session.createCriteria(User.class).add(Restrictions.eq("email", login)).uniqueResult();
			tx.commit(); 
			
		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		}
		
		return user;
	}
	
	
}
