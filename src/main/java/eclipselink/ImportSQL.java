package eclipselink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.SessionEvent;
import org.eclipse.persistence.sessions.SessionEventAdapter;
import org.eclipse.persistence.sessions.UnitOfWork;

/**
 * Allows EclipseLink to survive an app's dependency on the non-JPA-standardized
 * Hibernate "import.sql" feature. Totally non-standard. EclipseLink only!
 * <br>
 * Note: In JPA 2.1+ (EclipseLink 2.5.2+) you can get this functionality without
 * adding any code, just by adding this to persistence.xml:
 * <property name="javax.persistence.sql-load-script-source" value="/import.sql"/>
 * </br>
 * To use this workaround code, you MUST add this line to persistence.xml:
 * <property name="eclipselink.session.customizer" value="eclipselink.ImportSQL"/>
 * Original code posted by an Oracle (EclipseLink owner) employee for public use, at:
 * http://onpersistence.blogspot.ca/2010/03/running-sql-script-on-startup-in.html
 * <br>
 * XXX TODO See if we can find the value of eclipselink.ddl-generation
 * and only run this if it has 'create' as a substring.
 * @author Shaun Smith, Oracle, http://www.blogger.com/profile/03444889032778621661
 * @author Ian Darwin filled in some blanks and tweaked slightly
 */
public class ImportSQL implements SessionCustomizer {
	
	private void importSql(UnitOfWork unitOfWork, String fileName, boolean trace) {
		try (BufferedReader is = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(fileName)))) {

			String line; int lineNum = 0;
			while ((line = is.readLine())!= null) {
				++lineNum;
				if (line.length() == 0 || line.startsWith("--")) {
					continue;
				}
				if (trace) {
					System.err.println(line);
				}
				try {
					unitOfWork.executeNonSelectingSQL(line);
				} catch (Exception e) {
					System.err.println("Error at " + fileName + ":" + lineNum + ": " + e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void customize(Session session) throws Exception {
        session.getEventManager().addListener(new SessionEventAdapter() {
            @Override
            public void postLogin(SessionEvent event) {
            	System.err.println("ImportSQL.customize(...).new SessionEventAdapter() {...}.postLogin()");
                String fileName = (String) event.getSession().getProperty("import.sql.file");
                if (fileName == null) {
                	fileName = "/import.sql";
                }
                String logging = (String) event.getSession().getProperty("import.sql.trace");
                boolean tracing = logging != null && logging.equals("true");
                UnitOfWork unitOfWork = event.getSession().acquireUnitOfWork();
                importSql(unitOfWork, fileName, tracing);
                unitOfWork.commit();
            }
        });
    }
}