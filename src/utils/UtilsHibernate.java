package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class UtilsHibernate {

	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;


	@SuppressWarnings("deprecation")
	private static void createFactory(){
		try{
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml"); // fichier xml au niveau du répertoire src du projet
			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build(); 
			factory = configuration.buildSessionFactory(serviceRegistry);
		}	
		catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}

	public static SessionFactory instance() {
		if ( factory == null ){
			createFactory();
		}
		return factory;
	}
	
	public static void shutdown() {
		if ( factory != null ){
			factory.close();
		}

	}
}



