package jpa.features;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import domain.misc.Fish;
import domain.misc.FishPK;
import jpa.JpaUtil;

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
		
		final TypedQuery<Fish> fishQuery = em.createQuery("SELECT f FROM Fish f", Fish.class);
		for (Fish f : (List<Fish>)fishQuery.getResultList()) {
			System.out.println(f);
		}
	}
}
