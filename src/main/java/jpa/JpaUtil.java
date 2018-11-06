package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Common creational routines, similar to the ever-popular HibernateUtils class.
 * @author Ian Darwin
 */
public class JpaUtil {
	
	protected static EntityManagerFactory entityMgrFactory = null;
	protected static EntityManager entityManager = null;
	
	static {
		getEntityManagerFactory();
	}

	public synchronized static EntityManagerFactory getEntityManagerFactory() {
		long time = System.currentTimeMillis();
		entityMgrFactory = Persistence.createEntityManagerFactory("jpademo");
		long time2 = System.currentTimeMillis();
		System.out.printf("Created EntityManagerFactory in %f seconds%n", (time2 - time)/1000d);
		return entityMgrFactory;
	}

	public static EntityManager getEntityManager() {
		long time2 = System.currentTimeMillis();
		entityManager = entityMgrFactory.createEntityManager();
		long time3 = System.currentTimeMillis();
		System.out.printf("Created EntityManager in %f seconds%n", (time3 - time2)/1000d);
		return entityManager;
	}
	
	public static void close() {
		if (entityMgrFactory != null && entityMgrFactory.isOpen()) {
			entityMgrFactory.close();
		}
	}
}
