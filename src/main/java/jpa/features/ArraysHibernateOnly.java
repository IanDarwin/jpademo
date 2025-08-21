//package jpa.features;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.Persistence;
//
///** Demonstrate use of arrays, but only stores as Array under Hibernate Provider
// * (EclipseLink stores as a blob); ArraysEntity uses io.hypersistence.utils.hibernate.
// * add-on (formerly com.vladmihalcea:hibernate-types-52)
// */
//public class ArraysHibernateOnly {
//	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
//		EntityManager em = emf.createEntityManager();
//
//		ArraysEntity o = new ArraysEntity();
//		o.greetings = new String[] { "Hello", "Good Day", "Good Afternoon", "Good night" };
//
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		em.persist(o);
//
//		tx.commit();
//
//		System.out.println("Persisted object with String array");
//
//		em.close();
//	}
//}
//
