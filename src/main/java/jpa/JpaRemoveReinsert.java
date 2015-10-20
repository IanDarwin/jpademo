package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import domain.Person;

public class JpaRemoveReinsert {

	public static void main(String[] args) throws Exception {

		System.out.println("JpaSimple.main()");

		// These two steps would be done for you
		// were you running in an EE App Server.
		// Or just the EntityManager injected if you were using JavaEE or Spring
		EntityManagerFactory entityMgrFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = entityMgrFactory.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// Transaction 1 - Create an entity in the database.
		Person np = new Person("Tom", "Boots");
		System.out.println("Before insert: " + np);
		entityManager.persist(np);
		transaction.commit();
		
		int id = np.getId();
		System.out.println("After insert: " + np);
		
		// Transaction 2 - delete it
		transaction = entityManager.getTransaction();
		transaction.begin();

		entityManager.remove(np);
		transaction.commit();
		System.out.println("Deleted Person object " + np);
		if (entityManager.find(Person.class, id) != null) {
			System.out.println("Odd, it's not really gone?");
		}

		// Transaction 3 - Re-insert it
		transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(np);
		transaction.commit();
		System.out.println("Re-Created Person " + np);
		
		Person p3 = entityManager.find(Person.class, id);
		System.out.println(p3);

		entityManager.close();
	}
}
