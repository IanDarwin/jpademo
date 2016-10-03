package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import domain.Person;

public class JpaRollback {
	public static void main(String[] args) {

		System.out.println("JPARollback.main()");

		// These two steps would be done for you
		// were you running in an EE App Server.
		// Or just the EntityManager injected if you were using JavaEE or Spring
		EntityManagerFactory entityMgrFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			// Create an entity in the database.
			Person np = new Person("Tom", "Boots");
			System.out.println(np);
			entityManager.persist(np);
			transaction.commit();
			
			int npId = np.getId();
			System.out.println("Created Person with Id " + npId);
			
			// start another transaction
			transaction = entityManager.getTransaction();
			transaction.begin();
			
			Person tom = entityManager.find(Person.class, npId);
			tom.setLastName("Shoes");
			System.out.println("Rolling back transaction!");
			transaction.rollback();
			System.out.println("Tom's last name in memory is now " + tom.getLastName());
			
			boolean mergeBack = false;
			if (!mergeBack) {
				// Re-read it from the database
				tom = entityManager.find(Person.class, npId);
			} else {
				// Create a managed copy, in a transaction,
				// that will get written back.
				transaction = entityManager.getTransaction();
				transaction.begin();
				tom = entityManager.merge(tom);
				transaction.commit();
			}
			System.out.println("Tom's last name in database is now " + tom.getLastName());
		
		} finally {	
			if (entityManager != null)
				entityManager.close();
			if (entityMgrFactory != null)
				entityMgrFactory.close();
		}
	}

}
