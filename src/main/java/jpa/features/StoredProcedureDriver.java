package jpa.features;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import domain.media.Duration;
import domain.media.Track;

/**
 * Simple example of using Stored Procedure from within JPA,
 * using the "Native Query" escape route. N.B this assumes
 * you have done the db-specific work of creating the
 * stored procedure!
 * 
 * N.B. In Java EE 7 (JPA 2.x), you can simplify this via new annotations
 */
public class StoredProcedureDriver {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		// Set up some data
		Track t1 = new Track("Hop til ya stop", new Duration(240));
		em.persist(t1);
		Track t2 = new Track("Why not tonight?", new Duration(123));
		em.persist(t2);
		
		// A way to call SP from JPA
		Query query = em.createNativeQuery("getAllTracks()", Track.class);
		
		List<Track> list = query.getResultList();
		System.out.println("Got results, size " + list.size());
		for (Track t : list) {
			System.out.println(t.getTitle());
			System.out.println(t.getDuration());
		}
		transaction.commit();
	}

}
