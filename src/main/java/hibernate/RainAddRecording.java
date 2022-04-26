package hibernate;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import domain.model.Duration;
import domain.model.MusicRecording;
import domain.model.Track;

/**
 * Populate Hibernate Demo
 */
public class RainAddRecording {

	public static void main(String[] args) throws HibernateException {
		SessionFactory factory =
			new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

		Session hibSession = factory.openSession();
		Transaction tx = null;
		try {
			tx = hibSession.beginTransaction();

			// Create an unpopular recording
			MusicRecording rec = new MusicRecording();
			rec.setArtist("Ego Sum");
			rec.setTitle("Greatest Hits");
			rec.setPrice(9.67);
			List<Track> tracks = new ArrayList<Track>();
			tracks.add(new Track("Story of my life", new Duration(0,1,0)));
			tracks.add(new Track("All About Me", new Duration(0,2,22)));
			rec.setTracks(tracks);
			
			// Put it in
			hibSession.saveOrUpdate(rec);
			
			tx.commit();
			
			System.out.println("Added recording: " + rec);
		} catch (Throwable e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			hibSession.close();
			factory.close();
		}
	}

}
