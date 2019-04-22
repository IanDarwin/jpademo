package hibernate;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

public class HibernateGetJdbcConnection {

	public static void main(String[] args) {

		System.out.println("HibernateGetJdbcConnection.main()");

		Configuration cf = new Configuration();
		cf.configure();
		@SuppressWarnings("deprecation")
		SessionFactory sf = cf.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		
		// This is the "raw SQL" work we want to do.
		// Assumes you have granted create/drop permissions...
		Work myWork = new Work() {
			@Override
			public void execute(Connection conn) throws SQLException {
				conn.createStatement().execute("drop table if exists meh");
				conn.createStatement().execute("create table meh(id integer primary key)");
				System.out.println("I created a table!!");
			}
		};
		
		// Several ways of getting the connection
		
		// 1) Session.connection()
		// Session.connection() was deprecated in 3.x, REMOVED in 4.x
		// myWork.execute(session.connection());
		
		// 2) SessionFactoryImplementor - works but deprecated, removed in 6.x
		//SessionFactoryImplementor sessionFactoryImplementation = (SessionFactoryImplementor) sf;
	    //ConnectionProvider connectionProvider = sessionFactoryImplementation.getJdbcConnectionAccess();
	    //try {
		//	Connection conn = connectionProvider.obtainConnection();
		//	myWork.execute(conn);
		//	connectionProvider.closeConnection(conn);
		//} catch (SQLException e) {
		//	System.err.println("SessionFactoryImplementor plan failed");
		//}
	    
	    // 3) The current preferred way: session.doWork()
		session.doWork(myWork);
		
		// All done!
		tx.commit();
		session.close();
		sf.close();
		System.out.println("HibernateGetJdbcConnection done");
	}

}
