package hibernate;

import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.Person;

/** 
 * Demonstrate the fact that load() will return a proxy
 * (iff the object is not already in the current session!).
 * 
 * @author Ian Darwin
 */
public class HibernateGetVsLoad {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		System.out.println("HibernateGetVsLoad.main()");

		Session session = HibernateUtil.createSession();
		
		Transaction tx = session.beginTransaction();

		// Create an entity in the database.
		Person np = new Person("Tom", "Boots");
		System.out.println(np);
		session.save(np);
		tx.commit();

		int id = np.getId();
		System.out.println("Created Person with Id " + id);
		session.close();
		
		session = HibernateUtil.createSession();
		//tx = session.beginTransaction();
		Person p1 = (Person) session.get(Person.class, id);
		//tx.commit();
		session.close();
		
		session = HibernateUtil.createSession();
		//tx = session.beginTransaction();
		Person p2 = (Person) session.load(Person.class, id);
		System.out.println("Classes: " + p1.getClass() + "..." + p2.getClass());
		session.close();
		
		System.out.println(p1);
		try {
			System.out.println(p2);
			System.out.println("Busted! Did not throw LIE");
		} catch (LazyInitializationException lie) {
			System.out.println("Aha! Caught LIE as expected");
		}

		HibernateUtil.close();
	}

}
