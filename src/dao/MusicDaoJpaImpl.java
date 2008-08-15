package domain.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.dao.MusicDao;

/**
 * JPA implementation of MusicDAO
 */
public final class MusicDaoImpl implements MusicDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction entityTransaction;

	public MusicDaoImpl(){
		System.out.println("MusicDaoImpl.MusicDaoImpl()");
		emf = Persistence.createEntityManagerFactory("jpa-demo");
		em = emf.createEntityManager();
		System.out.println("MusicDaoImpl.MusicDaoImpl() JPA setup done");
	}

	public List<MusicRecording> findRecordingByPrice(double price) {
		return em.createQuery(
				"from rain.MusicRecording where price = " + price).getResultList();
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
	public List<MusicRecording> listMusicRecordings(){
		return em.createQuery("from rain.MusicRecording").getResultList();
	}
	
	/** Close this DAO
	 * @see domain.dao.MusicDao#close()
	 */
	public void close(){
		em.close();
		emf.close();

	}

}
