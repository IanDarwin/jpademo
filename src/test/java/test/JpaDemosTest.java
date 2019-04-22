package test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import jpa.JpaArray;
import jpa.JpaFindFail;
import jpa.JpaPaging;
import jpa.JpaRemoveReinsert;
import jpa.JpaRollback;
import jpa.JpaSimple;
import jpa.JpaUpdate;
import jpa.JpaUtil;
import jpa.RainListerJpa;
import jpa.TypedQueryDemo;

/**
 * Run a bunch of the "main program"-style demo classes as tests,
 * just to see if they blow up or not.
 */
@RunWith(Parameterized.class)
public class JpaDemosTest {
	
	/**
	 * We mock the EMF so the real one's close method won't get called.
	 */
	@BeforeClass
	public static void fakeEntityManagerFactory() {
		EntityManagerFactory realEMF = JpaUtil.getEntityManagerFactory();
		EntityManagerFactory mock = mock(EntityManagerFactory.class);
		when (mock.createEntityManager()).thenReturn(realEMF.createEntityManager());
		JpaUtil.setEntityManagerFactory(mock);
	}

	final static Class<?>[] mains = {
			// Can only include ones that use JpaUtil to get their EMF
			JpaArray.class,
			JpaFindFail.class,
			JpaPaging.class,
			// JpaQuery.class, // Requires manual intervention
			JpaRemoveReinsert.class,
			JpaRollback.class,
			JpaSimple.class,
			JpaUpdate.class,
			// ManyToManyJpa.class, // Doesn't work ATM
			RainListerJpa.class,
			TypedQueryDemo.class,
	};
	
	@Parameters(name = "{0}")
    public static List<Object[]> findFiles() {
        List<Object[]> results = new ArrayList<>(20);
        for (Class<?> c : mains) {
        	results.add(new Object[] {c});
        }
        return results;
	}

	private Class<?> clazz;

	public JpaDemosTest(Class<?> clazz) {
		this.clazz = clazz;
	}

	@Test
	public void test() throws Exception {
		System.out.println("START " + clazz.getCanonicalName());
		try {
			Method m = clazz.getMethod("main", String[].class);
			m.invoke(null, new Object[] {new String[0]});
		} finally {
			JpaUtil.close();
		}
	}
}
