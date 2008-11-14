package demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Person;

public class JPASimple {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		// Set up fake data
		Person np = new Person("Tom", "Boots");
		em.persist(np);
		transaction.commit();
		int id = np.getId();
		System.out.println("Created as " + id);
		
		Query query = em.createNativeQuery("from Person");

		List<Person> list = query.getResultList();
		System.out.println("Got results, size " + list.size());
		for (Person p : list) {
			System.out.println(
				p.getFirstName() + ' ' + p.getLastName());
		}
		
	}

}
