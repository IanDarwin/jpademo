package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import domain.Person;

public class JpaFindFail {
	public static void main(String[] args) {

		System.out.println("JPAFindFail.main()");

		EntityManagerFactory entityMgrFactory = JPAUtil.getEntityManagerFactory();
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			// Find an entity in the database.
			
			final int id = 98765;
			Person p = entityManager.find(Person.class, id);
			
			System.out.println("No Person with Id " + id + "; returned " + p);
		
		} finally {	
			if (entityManager != null)
				entityManager.close();
			if (entityMgrFactory != null)
				entityMgrFactory.close();
		}
	}

}
