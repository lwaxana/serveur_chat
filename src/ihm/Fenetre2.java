/**
 * 
 */
package ihm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import pojo.Serveur;
import utils.Utils;
import utils.UtilsHibernate;

/**
 * @author Lwaxana
 *
 */
public class Fenetre2 {

	
		
		private JFrame frame;
		private JPanel contentPane;
		private JTextField txtIP;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField_4;
		private JButton but_online;
		private JButton but_offline;
		
		private JCheckBox chk_online;
		private JCheckBox chk_offline;
		private Session session = null;
		
		
		
		
		public Fenetre2() {
			frame = new JFrame();
			session = UtilsHibernate.instance().openSession();
			frame.setResizable(false);
			frame.setTitle("Administration serveur");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 760, 568);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			frame.setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblIp = new JLabel("IP");
			lblIp.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblIp.setBounds(48, 61, 98, 14);
			contentPane.add(lblIp);
			
			JLabel lblNomServeur = new JLabel("Nom serveur :");
			lblNomServeur.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblNomServeur.setBounds(48, 86, 109, 30);
			contentPane.add(lblNomServeur);
			
			JLabel lblPort = new JLabel("Port :");
			lblPort.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblPort.setBounds(48, 130, 98, 14);
			contentPane.add(lblPort);
			
			JLabel lblPassword = new JLabel("Password :");
			lblPassword.setFont(new Font("Dialog", Font.PLAIN, 16));
			lblPassword.setBounds(48, 155, 98, 24);
			contentPane.add(lblPassword);
			
			JLabel label_stat = new JLabel("Statut");
			label_stat.setFont(new Font("Dialog", Font.PLAIN, 16));
			label_stat.setBounds(48, 190, 98, 14);
			contentPane.add(label_stat);
			
			txtIP = new JTextField();
			txtIP.setEditable(false);
			txtIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtIP.setBounds(167, 61, 200, 20);
			contentPane.add(txtIP);
			txtIP.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField_1.setColumns(10);
			textField_1.setBounds(167, 94, 200, 20);
			contentPane.add(textField_1);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField_2.setColumns(10);
			textField_2.setBounds(167, 130, 200, 20);
			contentPane.add(textField_2);
			
			textField_3 = new JTextField();
			textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			textField_3.setColumns(10);
			textField_3.setBounds(167, 160, 200, 20);
			contentPane.add(textField_3);
			
					
			but_online = new JButton("Put Online");
			but_online.setBounds(79, 251, 89, 23);
			contentPane.add(but_online);
			
			
				
				
			
			
			but_offline = new JButton("Put offline");
			but_offline.setBounds(193, 251, 128, 23);
			contentPane.add(but_offline);
			
						
			txtIP.setText(Utils.getLocalIp());
			
			chk_online = new JCheckBox("Online");
			chk_online.setEnabled(false);
			chk_online.setBounds(166, 189, 97, 23);
			contentPane.add(chk_online);
			
			chk_offline = new JCheckBox("Offline");
			chk_offline.setEnabled(false);
			chk_offline.setBounds(270, 189, 97, 23);
			contentPane.add(chk_offline);
			//update();			
			frame.setVisible(true);
			
			
		}
		/*
		public void update(){
			
			Transaction tx = null;
			try{
				tx = session.beginTransaction();
				serveur  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("IP", Utils.getLocalIp())).uniqueResult();
				if ( serveur != null){
					textField_1.setText(serveur.getNom());
					textField_2.setText(Integer.toString(serveur.getPort()));
					textField_3.setText(serveur.getPassword());
					if ( serveur.isOnline()){
						chk_online.setSelected(true);
					}
					else{
						chk_offline.setSelected(true);
					}
				}
				tx.commit(); 
			}
			catch (HibernateException e) {
				if (tx != null) tx.rollback(); 
				e.printStackTrace(); 
			}
		}
		
		public void setServeurOnline(){
			Transaction tx = null;
			try{
				tx = session.beginTransaction();
				serveur  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("IP", Utils.getLocalIp())).uniqueResult();
				if ( serveur != null){
					serveur.setOnline(true);
					session.saveOrUpdate(serveur);
					chk_online.setSelected(true);
					chk_offline.setSelected(false);
					
				}
				tx.commit(); 
			}
			catch (HibernateException e) {
				if (tx != null) tx.rollback(); 
				e.printStackTrace(); 
			}
		}
		
		public void putServerOffline(){
			Transaction tx = null;
			try{
				tx = session.beginTransaction();
				serveur  = (Serveur) session.createCriteria(Serveur.class).add(Restrictions.eq("IP", Utils.getLocalIp())).uniqueResult();
				if ( serveur != null){
					serveur.setOnline(false);
					session.saveOrUpdate(serveur);
					chk_online.setSelected(false);
					chk_offline.setSelected(true);
					serveur.close();
					
				}
				tx.commit(); 
			}
			catch (HibernateException e) {
				if (tx != null) tx.rollback(); 
				e.printStackTrace(); 
			}
		}
		*/
	}
	
	

