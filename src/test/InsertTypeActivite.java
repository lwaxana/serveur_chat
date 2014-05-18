/**
 * 
 */
package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Pays;
import pojo.Typeactivitesalon;
import pojo.Typeactiviteserveur;
import utils.UtilsHibernate;

/**
 * @author Lwaxana
 *
 */
public class InsertTypeActivite {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Session session = UtilsHibernate.instance().openSession();
		Transaction tx = null;
		InputStream ips = null;
		InputStreamReader ipsr = null;
		BufferedReader br = null;
		String fichier = "src/lib/type.txt";
		try{
			tx = session.beginTransaction(); 
		    ips=new FileInputStream(fichier); 
			ipsr=new InputStreamReader(ips);
			br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				/*
				Typeactivitesalon tas = new Typeactivitesalon();
				tas.setType(ligne.trim());
				session.save(tas);
				*/
				Typeactiviteserveur tas = new Typeactiviteserveur();
				tas.setType(ligne.trim());
				session.save(tas);
			}
			
			
			
			
			
			
			tx.commit(); 

		}
		catch (HibernateException e) {
			if (tx != null) tx.rollback(); 
			e.printStackTrace(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		    session.close();
		   
		}	
		

	}

}
