package test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.junit.Test;

import jpa.JpaUtil;

/** A very simple test just to ensure that JPA is set up correctly. */
public class JpaSetupTest {

	@Test
	public void testStartup() throws Exception {

		System.out.println("SetupTest.setupTest");

		EntityManagerFactory entityMgrFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = JpaUtil.getEntityManager();
		// If that didn't throw an exception then we know
		// that the JPA annotations are in good shape.
		entityManager.close();
		entityMgrFactory.close();
		
		System.out.println("Completed OK");
	}
}
