package demo;

import java.io.File;

public class DemoHelper {
	/** Create the temp directory used by HSQL
	 * for its clutter of temporary files.
	 */
	public static void setup() {
		final File hsqlDbDir = new File("tmp");
		if (hsqlDbDir.exists()) {
			for (File f : hsqlDbDir.listFiles()) {
				f.delete();
			}
		} else {
			hsqlDbDir.mkdirs();
		}
	}
}
