package jdbc;

/**
 * Demo of SQL Injection Attacks
 */
public class SqlInjection {
	public void breakable(String name) {
		String sql = "select * from students where name = '" + name + "';";
		executeQuery(sql);
	}

	private void executeQuery(String sql) {
		// This method would send the query string straight into the database
		// This is a bad thing...
	}
}
