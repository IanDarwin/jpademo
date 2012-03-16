package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

import domain.model.MusicRecording;

public class MusicRecordingDAOHibernate {

	SessionFactory factory =
		new Configuration().configure("/hibernate.cfg.xml").buildSessionFactory();

	/** Get list of recording by price */
	public List<MusicRecording> findRecordingsByPrice(double price) {

		Session hibSession = factory.openSession();
		Transaction tx = null;
		List<MusicRecording> list = null;
		try {
			tx = hibSession.beginTransaction();

			final Criteria criteria = hibSession.createCriteria(MusicRecording.class)
				.add(Expression.eq("price", price));
			list = criteria.list();
			
			tx.commit();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			hibSession.close();
		}
		return list;
	}
}
