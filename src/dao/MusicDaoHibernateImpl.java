package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

import domain.model.MusicRecording;

public class MusicRecordingDAOHibernate implements MusicDao {

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

	@Override
	public void saveMusicRecording(MusicRecording recording) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMusicRecording(MusicRecording recording) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MusicRecording getMusicRecording(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MusicRecording> listMusicRecordings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MusicRecording> findRecordingByPrice(double d) {
		// TODO Auto-generated method stub
		return null;
	}
}
