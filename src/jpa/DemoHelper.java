package demo;

import java.io.File;

public class DemoHelper {
	/** Create the temp directory used by HSQL
	 * for its clutter of temporary files.
	 */
	public static void setup() {
		final File hsqlDbDir = new File("tmp");
		hsqlDbDir.mkdir();
		hsqlDbDir.deleteOnExit();
	}
}
