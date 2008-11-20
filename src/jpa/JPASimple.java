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

		DemoHelper.setup();
		
		System.out.println("JPASimple.main()");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
	
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
		Address home = person.getHomeAddress();
		if (home != null && (home.getStreetAddress() != null || home.getCity() != null)) {
			em.persist(home);
		}
		// repeat for other Address fields
		em.persist(person);
		transaction.commit();
		System.out.println("Created Customer " + person + ", HomeAddress = " + person.getHomeAddress());
		
		Query query = em.createQuery("from Person p order by p.lastName");

		List<Person> list = query.getResultList();
		System.out.println("There are " + list.size() + " persons:");
		for (Person p : list) {
			System.out.println(
				p.getFirstName() + ' ' + p.getLastName());
		}
		
	}

}
