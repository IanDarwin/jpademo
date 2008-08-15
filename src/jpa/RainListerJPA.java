package domain.dao;

import java.util.List;

import domain.model.MusicDaoImpl;
import domain.model.MusicRecording;


/**
 * A main Driver for one JPA Demo
 */
public class Driver {

	public static void main(String[] args) {

		System.out.println("JPA Demo");
	
		MusicDao musicDAO = new MusicDaoImpl();
		List<MusicRecording> c =
            musicDAO.findRecordingByPrice(9.67);
		
		for (MusicRecording rec : c) {
			System.out.println(rec);	
		}

		MusicRecording recording1 = new MusicRecording("The Fray", "How to Save a Life", 9.99, "Rock", null);
		musicDAO.saveMusicRecording(recording1);
		System.out.printf("Added Music Recording %d.%n", recording1.getId());
		
		MusicRecording	recording2 = new MusicRecording("K.T.Tunstall", "Eye to the Telescope", 8.75, "Pop", null);
		musicDAO.saveMusicRecording(recording2);
		System.out.printf("Added Music Recording %d.%n", recording2.getId());
	}

}
