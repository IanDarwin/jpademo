package hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import dao.MusicRecordingDAOHibernate;
import domain.model.MusicRecording;
import domain.model.Track;

/**
 * Main program for Hibernate Demo
 * @version $Id: RainListerHibernate.java,v 1.2 2012/03/16 14:52:23 ian Exp $
 */
public class RainLister {

	public static void main(String[] args) throws HibernateException {
			final MusicRecordingDAOHibernate musicDAO = new MusicRecordingDAOHibernate();
			final List<MusicRecording> list = 
				musicDAO.findRecordingsByPrice(9.67);
			
			for (MusicRecording musicRecording : list) {
				System.out.println("Recording: " + musicRecording);
				for (Track t : musicRecording.getTracks() ) {
					System.out.println("\t" + t);
				}
			}
	}

}
