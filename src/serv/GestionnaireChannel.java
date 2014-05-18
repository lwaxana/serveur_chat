/**
 * 
 */
package serv;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import message.Message;
import message.Utilisateur;
import pojo.Salon;
import pojo.Serveur;
import pojo.User;
import typemessage.TypeMessage;
import utils.UtilsGestionnaireChannel;

/**
 * @author Lwaxana
 *
 */
public class GestionnaireChannel {
	
	private Serveur serveur;;
	private Set<Salon> listeSalon;
	private static GestionnaireChannel gestionnaireChannel;
	private List<User> listeUsers ;
	
	private GestionnaireChannel(){
		this.serveur = UtilsGestionnaireChannel.getServeur();
		this.listeSalon = UtilsGestionnaireChannel.getSalon();
		listeUsers = new ArrayList<>();
	}
	
	/**
	 * 
	 */
	public synchronized static GestionnaireChannel getInstance(){
		if ( gestionnaireChannel == null){
			gestionnaireChannel = new GestionnaireChannel();
		}
		return gestionnaireChannel;
	}
	
	/**
	 * 
	 * @param nomSalon
	 * @param user
	 */
	public synchronized boolean ajouterUserSalon(String str, User user){
		Salon salon = getSalon(str);
		if ( salon.ajouterUser(user) ){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param nomSalon
	 * @param user
	 */
	public synchronized void retirerUserSalon(Salon salon, User user){
		salon.retirerUser(user);
	}
	
	/**
	 * 
	 * @param salon
	 */
	public synchronized void creerSalon(Salon salon){
		listeSalon.add(salon);
	}
	
	public Salon getSalon(String nomSalon){
		Salon salon = null;
		for (Salon s : listeSalon){
			if ( s.getNom().equalsIgnoreCase(nomSalon)){
				salon = s;				
			}
		}
		return salon;
	}
	
	public synchronized String[] getChannelList(){
		String[] salons;
		salons = new String[listeSalon.size()];
		int i = 0;
		for ( Salon s : listeSalon){
			salons[i] = s.getNom();
			i++;
		}
		return salons;
		
	}
	
	public synchronized String[] getServeurInfo(){
		String[] liste;
		liste = new String[2];
		liste[0] = serveur.getNom();
		liste[1] = serveur.getIP();
		return liste;
	}
	
	public synchronized Utilisateur[] getUserList(Salon salon){
		return salon.getListUsers();
	}
	
	
	public synchronized void ajouterUser(User user){
		listeUsers.add(user);
		
	}
	
	public synchronized boolean checkNick(String str){
		
		int i = 0;
		boolean used = false;
		while ( i < listeUsers.size() && !used ){
			if ( listeUsers.get(i).getNick().equalsIgnoreCase(str)){
				used = true;
			}
			else{
				i++;
			}
		}
		System.out.println("nick utilisé :"+used);
		return used;
		/*
		boolean used = false;
		for ( User u : listeUsers){
			if ( u.getNick().equalsIgnoreCase(str)){
				used = true;
			}
		}
		return used;
		*/
	}
	
	public synchronized void changementNick(User u, String str){
		for ( Salon s : listeSalon){
			if ( s.contientUser(u)){
				Message message = new Message();
				message.setTypeMessage(TypeMessage.ChangementNickUser);
				message.setNomsalon(s.getNom());
				message.setUsers( gestionnaireChannel.getSalon(s.getNom()).getListUsers() );
				message.setMessage(str);
				s.envoyerMessage(message);
			}
		}
	}
	
	public synchronized void retirerUserGestionnaire(User u){
		listeUsers.remove(u);
	}
}
