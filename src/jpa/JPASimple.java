package demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Address;
import domain.Person;
import domain.sales.Customer;

public class JPASimple {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		System.out.println("JPASimple.main()");

		DemoHelper.setup();	// create hsqldb file
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			long time = System.currentTimeMillis();
			emf = Persistence.createEntityManagerFactory("jpademo");
			em = emf.createEntityManager();
			long time2 = System.currentTimeMillis();
			System.out.printf("Created EM in %f seconds%n", (time2 - time)/1000d);
		
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();

			// Create an entity in the database.
			Person np = new Person("Tom", "Boots");
			System.out.println(np);
			em.persist(np);
			transaction.commit();
			int id = np.getId();
			System.out.println("Created Person with Id " + id);
			
			transaction = em.getTransaction();
			transaction.begin();

			Customer person = new Customer("Happy", "User");
			person.getHomeAddress().setStreetAddress("123 Main St");
			Address home = person.getHomeAddress();
			if (home != null && (home.getStreetAddress() != null || home.getCity() != null)) {
				em.persist(home);
			} else {
				person.setHomeAddress(null);
			}
			Address work = person.getWorkAddress();
			if (work != null && (work.getStreetAddress() != null || work.getCity() != null)) {
				em.persist(work);
			} else {
				person.setWorkAddress(null);
			}
			em.persist(person);
			transaction.commit();
			System.out.println("Created Customer " + person + ", HomeAddress = " + person.getHomeAddress());
			
			Query query = em.createQuery("select p from Person p order by p.lastName");

			List<Person> list = query.getResultList();
			System.out.println("There are " + list.size() + " persons:");
			for (Person p : list) {
				System.out.println(
					p.getFirstName() + ' ' + p.getLastName());
			}
		} finally {	
			if (em != null)
				em.close();
			if (emf != null)
				emf.close();
		}
	}

}
