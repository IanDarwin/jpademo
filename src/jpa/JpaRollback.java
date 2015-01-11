package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Address;
import domain.HierBottom;
import domain.Person;
import domain.sales.Customer;

public class JpaRollback {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		System.out.println("JPARollback.main()");

		// These two steps would be done for you
		// were you running in an EE App Server.
		// Or just the EntityManager injected if you were using JavaEE or Spring
		EntityManagerFactory entityMgrFactory = JPAUtil.getEntityManagerFactory();
		EntityManager entityManager = JPAUtil.getEntityManager();
		
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
			tom = entityManager.find(Person.class, npId);
			// transaction = entityManager.getTransaction();
			// transaction.begin();
			// entityManager.merge(tom);
			System.out.println("Tom's last name in database is now " + tom.getLastName());
		
		} finally {	
			if (entityManager != null)
				entityManager.close();
			if (entityMgrFactory != null)
				entityMgrFactory.close();
		}
	}

}
