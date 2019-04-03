package hibernate;

import org.h2.util.DoneFuture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil for getting Hibernate Session
 */
public class HibernateUtil {
	private static SessionFactory factory;
	
	private static boolean dontCloseFactory = false; // Set true in HibernateDemosTest
	
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

	public static void close() {
		if (isDontCloseFactory()) {
			System.out.println("HibernateUtil.Test mode: ignoring close");
			return;
		}
		System.out.println("HibernateUtil.close()");
		factory.close();
	}

	/** FOR TESTING ONLY */
	public static void setDontCloseFactory(boolean dontCloseFactory) {
		HibernateUtil.dontCloseFactory = dontCloseFactory;
	}
}
