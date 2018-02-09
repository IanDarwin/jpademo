package hibernate.features;

import java.util.List;

import org.hibernate.Session;

import hibernate.HibernateUtil;
import jpa.features.SalesReportDTO;

/** 
 * An example of a Join and also an example of a DTO creation
 * @author Ian Darwin
 */
public class HibernateJoinDemo {

	final static String QUERY =
		// Note that the DTO created with NEW here is not a JPA Entity
		// so we have to give its full class name (if it were, we wouldn't).
		// The Join on p.sales will get a List<Sale> entities.
		"SELECT NEW jpa.features.SalesReportDTO(p.lastName, SUM(s.amount)) " + 
		"FROM SalesPerson p LEFT JOIN p.sales s GROUP BY p.lastName";
	
	static Session em;
	
	public static void main(String[] args) {
		em = HibernateUtil.createSession();
		System.out.println("Sales Report:");
		doReport();
		System.out.println("-------------");
		System.exit(0);
	}
	
	@SuppressWarnings("unchecked")
	public static void doReport() {
		int n = 0;
		for (SalesReportDTO data : (List<SalesReportDTO>)em.createQuery(QUERY).list()) {
			++n;
			System.out.printf("Name %s, Amount %s%n", data.getName(), data.getAmount());
		}
		if (n == 0) {
			System.out.println("NO SALES RECORDS FOUND");
		} else {
			System.out.println(n + " Sales Records Found");
		}
	}
}
