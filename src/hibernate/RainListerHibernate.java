package hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import dao.MusicDaoHibernateImpl;
import domain.model.MusicRecording;
import domain.model.Track;

/**
 * Main program for Hibernate Demo
 * @version $Id: RainListerHibernate.java,v 1.4 2012/03/16 15:14:47 ian Exp $
 */
public class RainListerHibernate {

	public static void main(String[] args) throws HibernateException {
			final MusicDaoHibernateImpl musicDAO = new MusicDaoHibernateImpl();
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
