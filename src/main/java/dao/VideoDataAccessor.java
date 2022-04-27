package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.model.VideoCategory;
import domain.model.VideoRecording;

/**
 *  This class implements a trivial database for video recordings.
 */
public class VideoDataAccessor implements RecordingDao<VideoRecording> {

	protected static Logger logger = Logger.getLogger("rainforest");

	private final EntityManagerFactory emf;
	private EntityManager em;

	/** Construct a data accessor.  */
	public VideoDataAccessor() {
		emf = Persistence.createEntityManagerFactory("rainforest");
	}

	/**
	 * Get the list of categories.
	 * @return Returns the categories.
	 */
	@Override
	public List<String> getCategories() {
		List<String> cats = new ArrayList<>();
		for (VideoCategory vc : VideoCategory.values())
			cats.add(vc.name());
		return cats;
	}

	/** Get the given recording given its id */
	@Override
	public VideoRecording getRecording(long id) {

		logger.fine("Getting recordings number: " + id);

		em = emf.createEntityManager();

		try {
			return em.find(VideoRecording.class, id);
		} finally {
			em.close();
		}
	}

	/**
	 * Returns a sorted list of recordings that match a given category
	 * @param category
	 *            the category for requested recordings.
	 * @return collection of <code>VideoRecording</code> objects
	 */
	@Override
	public List<VideoRecording> getRecordings(String category) {

		logger.fine("Getting a list of recordings for: " + category);

		em = emf.createEntityManager();

		try {
			return em
					.createQuery("from VideoRecording where category = ?", VideoRecording.class)
					.setParameter(1, category)
					.getResultList();
		} finally {
			em.close();
		}
	}

	/** Save the given recording. */
	@Override
	public long saveRecording(VideoRecording recording) {
		EntityTransaction entityTransaction = null;
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

	public void close() {
		emf.close();
	}
}
