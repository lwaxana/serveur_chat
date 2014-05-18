/**
 * 
 */
package utils;

import message.Message;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Administrateur;
import pojo.Adresse;
import pojo.Moderateur;
import pojo.Pays;
import pojo.User;
import typemessage.TypeMessage;

/**
 * @author Lwaxana
 *
 */
public class UtilsClientHandler {

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
		} finally {
			session.flush();
			session.close();

		}
		return user;

	}

	public static User inscrire(Message message){
		User user = null;
		Adresse adresse = null;
		Pays pays = null;
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 

			pays = null;
			pays = (Pays) session.createCriteria(Pays.class).add(Restrictions.eq("nompays", message.getPays())).uniqueResult();

			adresse = new Adresse();
			adresse.setRue(message.getRue());
			adresse.setNumero(message.getNumero());
			adresse.setZip(message.getZip());
			adresse.setVille(message.getVille());
			adresse.setPays(pays);
			session.persist(adresse);

			user = new User();
			user.setPrenom(message.getPrenom());
			user.setNom(message.getNom());
			user.setOnline(false);
			user.setBan(false);
			user.setEmail(message.getEmail());
			user.setPassword(message.getPassword());
			user.setAdresse(adresse);
			user.setNick("Default");
			session.persist(user);
			
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} finally {
			session.flush();
			session.close();

		}
		return user;

	}

	public static Message fillUserInfo(User user){
		User user2 = null;
		Message message = new Message();
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction(); 
			user2  = (User) session.createCriteria(User.class).add(Restrictions.eq("email", user.getEmail())).uniqueResult();
			tx.commit(); 
			message.setTypeMessage(TypeMessage.UserInfo);
			message.setEmail(user2.getEmail());
			message.setNick(user2.getNick());
			message.setNom(user2.getNom());
			message.setPrenom(user2.getPrenom());
			message.setRue(user2.getAdresse().getRue());
			message.setNumero(user2.getAdresse().getNumero());
			message.setVille(user2.getAdresse().getVille());
			message.setZip(user2.getAdresse().getZip());
			message.setPays(user2.getAdresse().getPays().getNompays());
			

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} finally {
			session.flush();
			session.close();
		}  


		return message;

	}

}
