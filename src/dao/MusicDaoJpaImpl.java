package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.model.MusicRecording;

/**
 * JPA implementation of MusicDAO
 */
public final class MusicDaoJpaImpl implements MusicDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction entityTransaction;

	public MusicDaoJpaImpl(){
		System.out.println("MusicDaoImpl.MusicDaoImpl()");
		emf = Persistence.createEntityManagerFactory("jpademo");
		em = emf.createEntityManager();
		System.out.println("MusicDaoImpl.MusicDaoImpl() JPA setup done");
	}

	@SuppressWarnings("unchecked")
	public List<MusicRecording> findRecordingsByPrice(double price) {
		return em.createQuery(
				"from MusicRecording where price = " + price).getResultList();
	}

	/**
	 * Save the given Recording
	 */
	public void saveMusicRecording(final MusicRecording recording) {
		try {
			entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.persist(recording);
			entityTransaction.commit();
		} catch (RuntimeException e) {
     		if(entityTransaction != null){
				entityTransaction.rollback();
			}
		}
	}

	/**
     * Delete the given MusicRecording
	 */
	public void deleteMusicRecording(final MusicRecording recording) {
		try {
			entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.remove(recording);
			entityTransaction.commit();

		} catch (RuntimeException e) {
			if(entityTransaction != null){
				entityTransaction.rollback();
			}
		}
	}

	/** Find the recording by its primary key.
	 * @see domain.dao.MusicDao#getMusicRecording(long)
	 */
	public MusicRecording getMusicRecording(final long id) {
		return em.find(MusicRecording.class, id);
	}

	/** List all the recordings.
	 * @see domain.dao.MusicDao#listMusicRecordings()
	 */
	@SuppressWarnings("unchecked")
	public List<MusicRecording> listMusicRecordings(){
		return em.createQuery("from MusicRecording").getResultList();
	}
	
	/** Close this DAO
	 * @see domain.dao.MusicDao#close()
	 */
	public void close(){
		em.close();
		emf.close();
	}
}
