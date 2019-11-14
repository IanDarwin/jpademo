package jpa.features;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



/** Demonstrate use of arrays, but only stores as Array under Hibernate Provider
 * (EclipseLink stores as a blob); compilation uses annotations in
 * required add-on API com.vladmihalcea:hibernate-types-52
 */
public class ArraysHibernateOnly {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();

		ArraysEntity o = new ArraysEntity();
		o.greetings = new String[] { "Hello", "Good Day", "Good Afternoon", "Good night" };

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(o);

		tx.commit();

		System.out.println("Persisted object with String array");

		em.close();
		emf.close();
	}
}

