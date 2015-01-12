package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import domain.array.ARecording;
import domain.array.ATrack;

/**
 * JPA has historically lacked support for array properties.
 * Hibernate has some support, though arrays are still not as well
 * supported as collections. This example does work with Hibernate 
 * @author Ian Darwin
 */
public class JpaArray {

	public static void main(String[] args) {

		System.out.println("JpaArray.main()");

		EntityManagerFactory entityMgrFactory = JPAUtil.getEntityManagerFactory();
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		try {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			// Create the Recording entity in the database.
			ARecording aRec = new ARecording("Mobey Hits");
			// Unlike with a List, with a Java language array, you seem to have
			// to persist the individual elements...
			ATrack t1 = new ATrack("Mellow Fellow");
			entityManager.persist(t1);
			ATrack t2 = new ATrack("Melody Mewey");
			entityManager.persist(t2);
			ATrack t3 = new ATrack("Mellencamp Movin'");
			entityManager.persist(t3);
			ATrack[] trax = { t1, t2, t3 };
			aRec.setTracks(trax);
			System.out.println(aRec);
			entityManager.persist(aRec);
			transaction.commit();
			
			long newRecId = aRec.getId();
			System.out.println("Created recording with Id " + newRecId);
			
			ARecording ar = entityManager.find(ARecording.class, newRecId);
			System.out.println(ar);
		} finally {	
			if (entityManager != null)
				entityManager.close();
			if (entityMgrFactory != null)
				entityMgrFactory.close();
		}
	}

}
