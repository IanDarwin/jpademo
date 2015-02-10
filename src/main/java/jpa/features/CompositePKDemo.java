package jpa.features;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import jpa.JpaUtil;
import domain.misc.Fish;
import domain.misc.FishPK;

public class CompositePKDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fish goldie = new Fish();
		goldie.setFishName("Goldie");
		FishPK pk = new FishPK(100, 100);
		goldie.setId(pk);
		
		EntityManager em = JpaUtil.getEntityManager();
		System.out.println("Using EM: " + em.getClass().getName());
		
		final EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(goldie);
		transaction.commit();
		
		final Query fishQuery = em.createQuery("SELECT f FROM Fish f");
		for (Fish f : (List<Fish>)fishQuery.getResultList()) {
			System.out.println(f);
		}
	}
}
