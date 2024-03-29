package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import domain.media.MusicRecording;

/**
 "A transaction can span more than one session" -- Max, Hibernate team.
 * https://forum.hibernate.org/viewtopic.php?f=1&t=959085&sid=0edccdb475ed7779304bed650877c0e5&start=15
 * The code below DOES NOT WORK because it is not the Hibernate Transaction object that spans, but
 * the global UserTransaction object, which can be started by the @Transactional annotation.
 * Note that Distributed Transactions require a full Java EE Application Server, not a standalone program.
 */
public class MultiSessionTX {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Configuration cf = new Configuration();
		cf.configure();
		SessionFactory sessionFactory = cf.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		// Persist an object
		Transaction tx = session.beginTransaction();
		MusicRecording rec = new MusicRecording();
		rec.setTitle("Cookin' wi' Java");
		rec.setArtist("The Unknown Programmer");
		session.save(rec);
		tx.commit();
		session.close();
		
		System.out.println("Saved: " + rec + " (id " + rec.getId() + ").");
		
		// Query some entities		
		Session session2 = sessionFactory.openSession();
		Query<MusicRecording> q = session2.createQuery("From MusicRecording");
		List<MusicRecording> list = q.list();
		System.out.println("Query found " + list.size() + " recordings");
		for (MusicRecording t : list) {
			System.out.println(t);
		}
		session2.close();
		
		// Shut it down
		sessionFactory.close();
	}
}
