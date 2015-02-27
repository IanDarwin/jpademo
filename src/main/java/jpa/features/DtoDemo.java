package jpa.features;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import jpa.JpaUtil;
import domain.model.VideoRecording;

/** 
 * An example of a DTO creation
 * @author Ian Darwin
 */
public class DtoDemo {
	static String query =
			// Note that the DTO created with NEW here is not a JPA Entity!
			"SELECT NEW jpa.features.VideoDto(v.title, v.price) from VideoRecording v";
	String name;
	int amount;
	private long id;
	static EntityManager em;
	
	public static void main(String[] args) {
		em = JpaUtil.getEntityManager();
		
		Query q = em.createQuery(query);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		VideoRecording vRec = new VideoRecording("Greatest Hits", 1984);
		vRec.setPrice(12.34);
		em.persist(vRec);
		em.flush();
		final List<VideoDto> resultList = q.getResultList();
		System.out.println("Video Prices:");
		for (VideoDto data : resultList) {
			System.out.printf("Name %s, Price %f%n", data.title, data.price);
		}
	}
}
