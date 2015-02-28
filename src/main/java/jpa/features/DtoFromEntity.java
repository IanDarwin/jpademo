package jpa.features;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import jpa.JpaUtil;
import domain.Person;
import domain.model.VideoRecording;

/** 
 * Try to use a real @Entity as a DTO instead of
 * making an explicit DTO class.
 * @author Ian Darwin
 */
public class DtoFromEntity {
	static String query =
		"select id, ptype, firstname, lastname from Person";
	String name;
	int amount;
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Person p = new Person("Greashy", "Smith");
		em.persist(p);
		em.flush();
		
		Query q = em.createNativeQuery(query, Person.class);
		final List<Person> resultList = q.getResultList();
		System.out.println("Person:");
		for (Person per : resultList) {
			System.out.println(per);
		}
		tx.commit();
	}
}
