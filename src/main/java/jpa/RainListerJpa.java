package jpa;

import java.util.List;

import dao.MusicDao;
import dao.MusicDaoJpaImpl;
import domain.model.MusicRecording;

/**
 * A main Driver to list recordings by price
 */
public class RainListerJpa {

	public static void main(String[] args) {

		System.out.println("JPA Demo");
		MusicDao musicDAO = new MusicDaoJpaImpl();

		MusicRecording recording1 = new MusicRecording("The Fray", "How to Save a Life", 9.67, "Rock", null);
		musicDAO.saveMusicRecording(recording1);
		System.out.printf("Added Music Recording %d.%n", recording1.getId());
		
		MusicRecording	recording2 = new MusicRecording("K.T.Tunstall", "Eye to the Telescope", 8.75, "Pop", null);
		musicDAO.saveMusicRecording(recording2);
		System.out.printf("Added Music Recording %d.%n", recording2.getId());

		List<MusicRecording> recs = musicDAO.findRecordingsByPrice(9.67);
		
		recs.forEach(System.out::println);	
	}

}
