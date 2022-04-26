package hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import dao.MusicDao;
import dao.MusicDaoHibernateImpl;
import domain.model.MusicRecording;
import domain.model.Track;

/**
 * Main program for Hibernate Demo
 */
public class RainListerHibernate {

	public static void main(String[] args) throws HibernateException {
			final MusicDao musicDAO = new MusicDaoHibernateImpl();

			MusicRecording recording1 = new MusicRecording("The Fray", "How to Save a Life", 9.67, "Rock", null);
			musicDAO.saveMusicRecording(recording1);
			System.out.printf("Added Music Recording %d.%n", recording1.getId());
			
			MusicRecording	recording2 = new MusicRecording("K.T.Tunstall", "Eye to the Telescope", 8.75, "Pop", null);
			musicDAO.saveMusicRecording(recording2);
			System.out.printf("Added Music Recording %d.%n", recording2.getId());
			
			final List<MusicRecording> list = 
				musicDAO.findRecordingsByPrice(9.67);
			
			list.forEach(mr -> {
				System.out.println("Recording: " + mr);
				mr.getTracks().forEach(t -> System.out.println("\t" + t));
			});

		musicDAO.close();
	}

}
