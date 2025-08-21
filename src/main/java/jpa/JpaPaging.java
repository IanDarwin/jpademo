package jpa;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import domain.Person;

/** Shows use of Query properties to break a list into pages,
 * as you would on a web view of a long list. This is sometimes
 * called the Value List Handler pattern or the Fast Track pattern.
 * @author Ian Darwin
 */
public class JpaPaging {
	
	public static void main(String[] args) {

		System.out.println("JpaPaging.main()");

		// These two steps would be done for you
		// were you running in an EE App Server.
		// Or just the EntityManager injected if you were using JavaEE or Spring
		EntityManagerFactory entityMgrFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = JpaUtil.getEntityManager();

		int people = entityManager.createQuery("select count(p) from Person p").getFirstResult();
		System.out.printf("There are %d people%n", people);
		int pageSize = 10, pageNumber = 0;
		TypedQuery<Person> query = 
				entityManager.createQuery("select p from Person p order by p.lastName",
						Person.class);
		List<Person> list = null;
		do {
			query.setFirstResult((pageNumber) * pageSize);
			query.setMaxResults(pageSize);
			list = query.getResultList();
			if (list.size() > 0) {
				System.out.printf("Page %d has %d persons:%n", pageNumber, list.size());
				for (Person p : list) {
					System.out.println(
							p.getFirstName() + ' ' + p.getLastName());
				}
				System.out.println();
			}
			++pageNumber;
		} while (list.size() > 0);

		if (entityManager != null)
			entityManager.close();
		if (entityMgrFactory != null)
			entityMgrFactory.close();
	}

}
