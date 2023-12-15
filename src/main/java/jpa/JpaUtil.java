package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Common creational routines, similar to the ever-popular HibernateUtils class.
 * @author Ian Darwin
 */
public class JpaUtil {
	
	protected static EntityManagerFactory entityMgrFactory;
	protected static EntityManager entityManager;
	private static boolean testing;
	
	static {
		entityMgrFactory = getEntityManagerFactory();
	}

	public synchronized static EntityManagerFactory getEntityManagerFactory() {
		long time = System.currentTimeMillis();
		entityMgrFactory = Persistence.createEntityManagerFactory("jpademo");
		long time2 = System.currentTimeMillis();
		System.out.printf("Created EntityManagerFactory in %f seconds%n", (time2 - time)/1000d);
		return entityMgrFactory;
	}
	
	/** FOR TESTING ONLY */
	public synchronized static void setEntityManagerFactory(EntityManagerFactory mockEMF) {
		testing = true;
		JpaUtil.entityMgrFactory = mockEMF;
	}

	public static EntityManager getEntityManager() {
		long time2 = System.currentTimeMillis();
		entityManager = entityMgrFactory.createEntityManager();
		long time3 = System.currentTimeMillis();
		System.out.printf("Created EntityManager in %f seconds%n", (time3 - time2)/1000d);
		return entityManager;
	}
	
	public static void close() {
		// In a real app this would invoke entityManager.close();
		// But it is static and causes errors when running the tests.
		if (!testing && entityMgrFactory != null && entityMgrFactory.isOpen()) {
			entityMgrFactory.close();
		}

	}
}
