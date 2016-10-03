package dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Expression;

import domain.model.MusicRecording;

@SuppressWarnings("deprecation")
public class MusicDaoHibernateImpl implements MusicDao {

	final static SessionFactory factory =
		new AnnotationConfiguration().configure("/hibernate.cfg.xml").buildSessionFactory();

	@Override
	public MusicRecording getMusicRecording(long id) {
		Session hibSession = factory.openSession();
		Transaction tx = null;
		try {
			tx = hibSession.beginTransaction();
			return (MusicRecording) hibSession.get(MusicRecording.class, id);
		} finally {
			tx.commit();
			hibSession.close();
		}
	}
	
	@Override
	public List<MusicRecording> listMusicRecordings() {
		Session hibSession = factory.openSession();
		Transaction tx = null;
		try {
			tx = hibSession.beginTransaction();
			return (List<MusicRecording>) hibSession.createQuery("from MusicRecording");
		} finally {
			tx.commit();
			hibSession.close();
		}
	}
	
	/** Get list of recording by price */
	@Override
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
			
		} finally {
			hibSession.close();
		}
		return list;
	}

	@Override
	public void saveMusicRecording(MusicRecording recording) {
		Session hibSession = factory.openSession();
		Transaction tx = null;
		try {
			tx = hibSession.beginTransaction();
			hibSession.saveOrUpdate(recording);
			tx.commit();
		} finally {
			hibSession.close();
		}
	}

	@Override
	public void deleteMusicRecording(MusicRecording recording) {
		Session hibSession = factory.openSession();
		Transaction tx = null;
		try {
			tx = hibSession.beginTransaction();
			hibSession.delete(recording);
			tx.commit();
		} finally {
			hibSession.close();
		}
	}

	@Override
	public void close() {
		factory.close();
	}
}
