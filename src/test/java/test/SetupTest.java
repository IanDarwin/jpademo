package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import jpa.JPAUtil;

import org.junit.Test;

/** A very simple test just to ensure that JPA is set up correctly. */
public class SetupTest {

	@Test
	public void testStartup() throws Exception {

		System.out.println("SetupTest.setupTest");

		EntityManagerFactory entityMgrFactory = JPAUtil.getEntityManagerFactory();
		EntityManager entityManager = JPAUtil.getEntityManager();
	}
}
