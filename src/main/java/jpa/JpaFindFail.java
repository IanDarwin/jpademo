package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import domain.Person;

public class JpaFindFail {
	public static void main(String[] args) {

		System.out.println("JPAFindFail.main()");

		EntityManagerFactory entityManagerFactory = JPAUtil.getEntityManagerFactory();
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManager();

			// Find an entity in the database.
			
			final int id = 98765;
			Person p = entityManager.find(Person.class, id);
			
			System.out.println("No Person with Id " + id + "; returned " + p);
		
			// Create a query with a where clause on a non-existent primary key to show what happens
			Query q2 = entityManager.createQuery("from Person c where c.id = 98765");
			@SuppressWarnings("unchecked")
			List<Person> list = q2.getResultList();
			System.out.println("getResultList return a list of length " + list.size());

			Person p2 = (Person) q2.getSingleResult();
			throw new RuntimeException("getSingleResult() failed to throw expected exception, returned " + p2);
		} catch (NoResultException nre) {
			System.out.println("Failing getSingleResult() threw expected NoResultException!");
		} finally {	
			if (entityManager != null)
				entityManager.close();
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
	}

}
