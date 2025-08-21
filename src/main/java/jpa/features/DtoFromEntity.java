package jpa.features;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import domain.Person;
import jpa.JpaUtil;

/** 
 * Try to use a real @Entity as a DTO instead of
 * making an explicit DTO class.
 * @author Ian Darwin
 */
public class DtoFromEntity {
	static String query =
		"select id, ptype, firstName, lastName from Person";
	String name;
	int amount;
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Person p = new Person("Greashy", "Smith");
		em.persist(p);
		em.flush();
		
		@SuppressWarnings("unchecked")
		final List<Person> resultList = em.createNativeQuery(query, Person.class).getResultList();
		System.out.println("Person:");
		for (Person per : resultList) {
			System.out.println(per);
		}
		tx.commit();
	}
}
