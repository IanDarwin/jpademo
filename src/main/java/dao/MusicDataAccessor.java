package dao;

import java.util.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import domain.media.MusicCategory;
import domain.media.MusicRecording;

/**
 * JPA implementation of MusicDAO.
 * Unlike our general rules, this DAO does control transactions.
 * The app is simple enough that it's OK.
 */
public final class MusicDataAccessor implements RecordingDao<MusicRecording>, AutoCloseable {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction entityTransaction;

	public MusicDataAccessor(){
		emf = Persistence.createEntityManagerFactory("jpademo");
	}

	/**
	 * Returns a sorted list of the categories for the recordings.
	 * Only ones in the enum will be included.
	 */
	public List<String> getCategories() {
		List<String> cats = new ArrayList<>();
		for (MusicCategory vc : MusicCategory.values())
			cats.add(vc.name());
		return cats;
	}

	/**
	 * Save the given Recording
	 */
	@Override
	public long saveRecording(final MusicRecording recording) {
		try {
			em = emf.createEntityManager();
			entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.persist(recording.getDuration());
			em.flush();
			em.persist(recording);
			entityTransaction.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
     		if (entityTransaction != null){
				entityTransaction.rollback();
			}
		} finally {
			if (em != null)
				em.close();
		}
		return recording.getId();
	}

	/** Find the recording by its primary key.
	 * @see dao.RecordingDao#getRecording(long)
	 */
	@Override
	public MusicRecording getRecording(final long id) {
		em = emf.createEntityManager();
		try {
			return em.find(MusicRecording.class, id);
		} finally {
			em.close();
		}
	}

	/** List all the recordings.
	 * @see dao.RecordingDao#getRecordings(String category)
	 */
	@Override
	public List<MusicRecording> getRecordings(String category){
		em = emf.createEntityManager();
		try {
			return em
				.createQuery("from MusicRecording where category = ?1", MusicRecording.class)
				.setParameter(1, category)
				.getResultList();
		} finally {
			em.close();
		}
	}
	
	/** Close this DAO
	 */
	public void close(){
		emf.close();
	}
}
