/**
 * 
 */
package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Administrateur;
import pojo.User;
import utils.UtilsHibernate;

/**
 * @author Lwaxana
 *
 */
public class Test6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User user = null;
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			user  = (User) session.createCriteria(User.class).add(Restrictions.eq("email", "admin")).uniqueResult();
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} finally {
			session.flush();
			session.close();

		}
		
		System.out.println(user instanceof Administrateur);

	}

}
