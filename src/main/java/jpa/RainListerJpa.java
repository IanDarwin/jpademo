package jpa;

import java.util.List;

import dao.MusicDataAccessor;
import domain.model.MusicRecording;

/**
 * A main Driver to list recordings by price
 */
public class RainListerJpa {

	public static void main(String[] args) {

		System.out.println("JPA Demo");
		MusicDataAccessor musicDAO = new MusicDataAccessor();

		MusicRecording recording1 = new MusicRecording("The Fray", "How to Save a Life", 9.67, "Rock", null);
		musicDAO.saveRecording(recording1);
		System.out.printf("Added Music Recording %d.%n", recording1.getId());
		
		MusicRecording	recording2 = new MusicRecording("K.T.Tunstall", "Eye to the Telescope", 8.75, "Pop", null);
		musicDAO.saveRecording(recording2);
		System.out.printf("Added Music Recording %d.%n", recording2.getId());

		List<MusicRecording> recs = musicDAO.getRecordings("Jazz");
		recs.forEach(System.out::println);
		
		musicDAO.close();
	}

}
