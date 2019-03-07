package test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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

@RunWith(Parameterized.class)
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
	
	@Parameters
	public static List<Class<?>> params() {
		return Arrays.asList(mains);
	}
	
	private Class<?> clazz;
	
	public HibernateDemosTest(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	@Test
	public void test() throws Exception {
		System.out.println("START " + clazz);
		Method m = clazz.getMethod("main", String[].class);
		m.invoke(null, new Object[] {new String[0]});
	}

}
