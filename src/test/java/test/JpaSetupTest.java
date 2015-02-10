package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import jpa.JpaUtil;

import org.junit.Test;

/** A very simple test just to ensure that JPA is set up correctly. */
public class JpaSetupTest {

	@Test
	public void testStartup() throws Exception {

		System.out.println("SetupTest.setupTest");

		EntityManagerFactory entityMgrFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		entityManager.close();
		entityMgrFactory.close();
		
		System.out.println("Completed OK");
	}
}
