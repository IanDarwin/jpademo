package jdbc;

/**
 * Demo of SQL Injection Attacks
 */
public class SqlInjection {
	public static void main(String[] args) {
		String name = readNameFromUser();
		getStudent(name);
	}

	public static void getStudent(String name) {
		String sql = "select * from students where name = '" + name + "';";
		String student = executeQuery(sql);
	}

	private static String executeQuery(String sql) {
		System.out.println("Executing query: " + sql);
		// This method would send the query string straight into the database
		// This is a bad thing...
		return null;
	}

	private static String readNameFromUser() {
		return "Robert'; drop table students; --";	// XKCD Exploits of a Mom
	}
}
