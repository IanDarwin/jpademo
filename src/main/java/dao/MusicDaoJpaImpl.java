package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.model.MusicRecording;

/**
 * JPA implementation of MusicDAO.
 * Unlike our general rules, this DAO does control transactions.
 * It probably shouldn't.
 */
public final class MusicDaoJpaImpl implements MusicDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction entityTransaction;

	public MusicDaoJpaImpl(){
		System.out.println("MusicDaoImpl.MusicDaoImpl()");
		emf = Persistence.createEntityManagerFactory("jpademo");
		System.out.println("MusicDaoImpl.MusicDaoImpl() JPA setup done");
	}

	public List<MusicRecording> findRecordingsByPrice(double price) {
		try {
		em = emf.createEntityManager();
		return em.createQuery(
				"from MusicRecording where price = " + price, MusicRecording.class).getResultList();
		} finally {
			if (em != null)
				em.close();
		}
	}

	/**
	 * Save the given Recording
	 */
	public void saveMusicRecording(final MusicRecording recording) {
		try {
			em = emf.createEntityManager();
			entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.persist(recording);
			entityTransaction.commit();
			System.out.println("MusicDaoJpaImpl.saveMusicRecording()" + recording.getId());
		} catch (RuntimeException e) {
			e.printStackTrace();
     		if (entityTransaction != null){
				entityTransaction.rollback();
			}
		} finally {
			if (em != null)
				em.close();
		}
	}

	/**
     * Delete the given MusicRecording
	 */
	public void deleteMusicRecording(final MusicRecording recording) {
		try {
			em = emf.createEntityManager();
			entityTransaction = em.getTransaction();
			entityTransaction.begin();
			em.remove(recording);
			entityTransaction.commit();
			em.close();
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
		return emf.createEntityManager().find(MusicRecording.class, id);
	}

	/** List all the recordings.
	 * @see domain.dao.MusicDao#listMusicRecordings()
	 */
	@SuppressWarnings("unchecked")
	public List<MusicRecording> listMusicRecordings(){
		em = emf.createEntityManager();
		return em.createQuery("from MusicRecording").getResultList();
	}
	
	/** Close this DAO
	 * @see domain.dao.MusicDao#close()
	 */
	public void close(){
		emf.close();
	}
}
