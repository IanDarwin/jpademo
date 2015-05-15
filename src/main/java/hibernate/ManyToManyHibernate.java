package hibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import domain.model.Actor;
import domain.model.VideoRecording;

/**
 * Many-To-Many Hibernate Demo
 */
public class ManyToManyHibernate {

	public static void main(String[] args) throws HibernateException {
		SessionFactory factory =
			new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

		Session hibSession = factory.openSession();
		Transaction tx = null;
		try {
			tx = hibSession.beginTransaction();

			// Create a new recording
			VideoRecording v = new VideoRecording();
			v.setTitle("Star Wars IV: A New Hope");
			v.setDirector("George Lucas");
			v.setPrice(9.67);
			Set<Actor> actors = new HashSet<Actor>();
			Actor a = new Actor();
			v.addActor(a);
			a.setFirstName("Harrison");
			a.setLastName("Ford");
			actors.add(a);
			a = new Actor();
			v.addActor(a);
			a.setFirstName("Mark");
			a.setLastName("Hammill");
			actors.add(a);
			a = new Actor();
			v.addActor(a);
			a.setFirstName("Carrie");
			a.setLastName("Fisher");
			actors.add(a);
			
			v.setActors(actors);
			
			// Put it in
			hibSession.save(v);
			
			tx.commit();
			int videoId = v.getId();
			
			tx = hibSession.beginTransaction();
			VideoRecording vid = (VideoRecording) hibSession.load(VideoRecording.class, videoId);
			System.out.println("Actors in recording " + vid);
			for (Actor ac : vid.getActors()) {
				System.out.println(ac);
			}
			tx.commit();
			
		} catch (Throwable e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			hibSession.close();
		}
	}

}
