package jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.media.Actor;
import domain.media.VideoRecording;

public class ManyToManyJpa {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		System.out.println("ManyToManyJPA.main()");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			// N.B. Most data for this demo are loaded via the sql load script

			// Show Actors appearing in "Star Wars IV"
			final Query swQuery = em.createQuery(
				"select v from VideoRecording v where v.title = 'Star Wars IV: A New Hope'");
			VideoRecording swIV = (VideoRecording) swQuery.getSingleResult();
			System.out.println("Video: " + swIV);
			
			List<Actor> starWarsActors = swIV.getActors();
			System.out.println("The following actors appear in " + swIV);
			for (Actor a : starWarsActors) {
				System.out.println("\t" + a);
			}
			System.out.println();
			
			// Update and show videos that Harrison Ford appears in
			final Query hfQuery = em.createQuery("select a from Actor a where a.firstName = 'Harrison' and a.lastName = 'Ford'");
			Actor ford = (Actor) hfQuery.getSingleResult();
			System.out.println("Found actor " + ford);
			VideoRecording vr = new VideoRecording("Indiana Jones: Kingdom of the Crystal Skull", 2008);
			vr.addActor(ford);
			em.persist(vr);
			
			// This could be done as ford.getVideos(), but is
			// here to show off a bit of JPQL syntax
			// (and the ability to use "order by" :-) ).
			Collection<VideoRecording> fordVideos;
			final Query videosByActorQuery = 
				em.createQuery("Select v from VideoRecording v join v.actors a where a = ?1 order by v.year");
			videosByActorQuery.setParameter(1, ford);
			fordVideos = videosByActorQuery.getResultList();
			System.out.println("Doing it again with getVideos()");
			ford.getVideos().forEach(p -> System.out.println(p));
			
			System.out.println(ford + " appears in these videos:");
			for (VideoRecording v : fordVideos) {
				System.out.println("\t" + v);
			}
			System.out.println();
		} finally {
			em.close();
			emf.close();
		}
	}

}
