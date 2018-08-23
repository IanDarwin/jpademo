package test;

import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Test;

import hibernate.CriteriaDemo;
import hibernate.HibernateGetJdbcConnection;
import hibernate.HibernateGetVsLoad;
import hibernate.HibernateSimple;
import hibernate.HibernateUtil;
import hibernate.ManyToManyHibernate;
import hibernate.ManyToOneDemo;
import hibernate.MultiSessionTX;
import hibernate.RainAddRecording;
import hibernate.RainListerHibernate;

public class HibernateDemosTest {

	final static Class<?>[] mains = {
			CriteriaDemo.class,
			HibernateGetJdbcConnection.class,
			HibernateGetVsLoad.class,
			HibernateSimple.class,
			HibernateUtil.class,
			ManyToManyHibernate.class,
			ManyToOneDemo.class,
			MultiSessionTX.class,
			RainAddRecording.class,
			RainListerHibernate.class,
	};
	
	@Test
	public void test() {
		boolean failed = false;
		
		for (Class<?> c : mains) {
			System.out.println("START " + c.getCanonicalName());
			try {
				Object demo = c.newInstance();
				Method m = c.getMethod("main", String[].class);
				m.invoke(null, new Object[] {new String[0]});
			} catch (Exception ex) {
				failed = true;
				ex.printStackTrace();
			}
		}
		if (failed) {
			fail("There were Hibernate Demo Failures");
		}
	}

}
