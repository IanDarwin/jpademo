package jpa.features;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import domain.model.VideoRecording;
import jpa.JpaUtil;

/** 
 * An example of a DTO creation
 * @author Ian Darwin
 */
public class DtoDemo {
	static String query =
		// Note that the DTO created with NEW here is not a JPA Entity!
		// And must be fully qualified since JPA has no "import"
		"SELECT NEW jpa.features.VideoDto(v.title, v.price) from VideoRecording v";
	String name;
	int amount;
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		TypedQuery<VideoDto> q = em.createQuery(query, VideoDto.class);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		VideoRecording vRec = new VideoRecording("Greatest Hits", 1984);
		vRec.setPrice(12.34);
		em.persist(vRec);
		em.flush();
		final List<VideoDto> resultList = q.getResultList();
		System.out.println("Video Prices:");
		for (VideoDto data : resultList) {
			System.out.printf("Name %s, Price $%-5.2f%n", data.title, data.price);
		}
		tx.commit();
	}
}
