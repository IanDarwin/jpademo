package demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.model.Actor;
import domain.model.VideoRecording;

public class ManyToManyDemo {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			// N.B. Most data for this demo are loaded via "import.sql"

			// Show Actors appearing in "Star Wars IV"
			final Query swQuery = em.createQuery("from VideoRecording where title = 'Star Wars IV: A New Hope'");
			VideoRecording swIV = (VideoRecording) swQuery.getSingleResult();
			System.out.println("Found video: " + swIV);
			final Query actorsByVideoQuery =
				em.createQuery("Select a from Actor a join a.videos v where v = ?1 order by a.lastName");
			actorsByVideoQuery.setParameter(1, swIV);
			List<Actor> starWarsActors = actorsByVideoQuery.getResultList();
			System.out.println("The following actors are credited in " + swIV);
			for (Actor a : starWarsActors) {
				System.out.println("\t" + a);
			}
			System.out.println();
			
			// Update and show videos that Harrison Ford appears in
			final Query hfQuery = em.createQuery("from Actor where firstName = 'Harrison' and lastName = 'Ford'");
			Actor ford = (Actor) hfQuery.getSingleResult();
			System.out.println("Found actor " + ford);
			VideoRecording vr = new VideoRecording("Indiana Jones: Kingdom of the Crystal Skull", 2008);
			vr.addActor(ford);
			em.persist(vr);
			final Query videosByActorQuery = 
				em.createQuery("Select v from VideoRecording v join v.actors a where a = ?1 order by v.year");
			videosByActorQuery.setParameter(1, ford);
			List<VideoRecording> fordVideos = videosByActorQuery.getResultList();
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
