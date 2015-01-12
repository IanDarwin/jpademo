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
 * Hibernate "import.sql" feature. Totally non-standard.
 * You MUST add this line to persistence.xml:
 * <property name="eclipselink.session.customizer" value="eclipselink.ImportSQL"/>
 * Original code posted by an Oracle (EclipseLink owner) employee for public use, at:
 * http://onpersistence.blogspot.ca/2010/03/running-sql-script-on-startup-in.html
 * @author Shaun Smith, http://www.blogger.com/profile/03444889032778621661
 * @author Ian Darwin filled in some blanks and tweaked slightly
 */
public class ImportSQL implements SessionCustomizer {
	
	private void importSql(UnitOfWork unitOfWork, String fileName) {
		try (BufferedReader is = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(fileName)))) {

			String line;
			while ((line = is.readLine())!= null) {
				if (line.length() == 0 || line.startsWith("--")) {
					continue;
				}
				unitOfWork.executeNonSelectingSQL(line);
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
                String fileName = (String) event.getSession().getProperty("import.sql.file");
                if (fileName == null) {
                	fileName = "/import.sql";
                }
                UnitOfWork unitOfWork = event.getSession().acquireUnitOfWork();
                importSql(unitOfWork, fileName);
                unitOfWork.commit();
            }
        });
    }
}