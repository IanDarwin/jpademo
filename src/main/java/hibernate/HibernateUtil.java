package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil for Hibernate 3
 */
@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static SessionFactory factory;
	
	static {
		final Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}
	
	public static Session createSession() {
		return factory.openSession();
	}
	public static Session findSession() {
		return factory.getCurrentSession();
	}
}
