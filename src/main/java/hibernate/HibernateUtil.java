package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil for getting Hibernate Session
 */
public class HibernateUtil {
	private static final Configuration configuration;
	private static SessionFactory factory;
	
	private static boolean dontCloseFactory = false; // Set true in HibernateDemosTest
	
	static {
		configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	public static Session createSession() {
		return factory.openSession();
	}

	public static Session findSession() {
		return factory.getCurrentSession();
	}

	public static void close() {
		if (dontCloseFactory) {
			System.out.println("HibernateUtil.Test mode: ignoring close");
			return;
		}
		System.out.println("HibernateUtil.close()");
		factory.close();
	}
}
