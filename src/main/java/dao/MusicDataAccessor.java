package dao;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.model.MusicCategory;
import domain.model.MusicRecording;

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
		emf = Persistence.createEntityManagerFactory("rainforest");
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
				.createQuery("from MusicRecording where category = ?", MusicRecording.class)
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
