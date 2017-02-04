package hibernate;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import domain.model.VideoRecording;

/** 
 * Demonstrates the Hibernate Criteria interface.
 * Should probably have more fields, and better test data in import.sql
 */
public class CriteriaDemo {
	@Resource
	Session session;

	List<VideoRecording> doSearch(String titleStr, String producerName) {
		if (session == null) {
			session = HibernateUtil.createSession();
		}
		if (!session.getTransaction().isActive())
			session.getTransaction().begin();
		Criteria qb = session.createCriteria(VideoRecording.class);
		if (titleStr != null) {
			qb.add(Restrictions.like("title", wildCard(titleStr)));
		}
		if (producerName != null) {
			qb.add(Restrictions.like("producer", wildCard(producerName)));
		}
		return qb.list();
	}
	
	private String wildCard(String str) {
		return str.indexOf('%') == -1 ? "%"+str+"%" : str;
	}
	
	public static void main(String[] args) {
		for (VideoRecording mRec : new CriteriaDemo().doSearch("Star", null)) {
			System.out.println(mRec);
		}
	}
}