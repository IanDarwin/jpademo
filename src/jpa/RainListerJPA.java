package jpa;

import java.util.List;

import domain.dao.MusicDao;
import domain.dao.MusicDaoImpl;
import domain.model.MusicRecording;


/**
 * A main Driver for one JPA Demo
 */
public class DAODemo {

	public static void main(String[] args) {

		System.out.println("JPA Demo");
		MusicDao musicDAO = new MusicDaoImpl();

		MusicRecording recording1 = new MusicRecording("The Fray", "How to Save a Life", 9.99, "Rock", null);
		musicDAO.saveMusicRecording(recording1);
		System.out.printf("Added Music Recording %d.%n", recording1.getId());
		
		MusicRecording	recording2 = new MusicRecording("K.T.Tunstall", "Eye to the Telescope", 8.75, "Pop", null);
		musicDAO.saveMusicRecording(recording2);
		System.out.printf("Added Music Recording %d.%n", recording2.getId());

		List<MusicRecording> c =
            musicDAO.findRecordingByPrice(9.99);
		
		for (MusicRecording rec : c) {
			System.out.println(rec);	
		}
	}

}
