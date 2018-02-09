package jpa.features;

import javax.persistence.EntityManager;

import jpa.JpaUtil;

/** 
 * An example of a Join and also an example of a DTO creation
 * @author Ian Darwin
 */
public class JpaJoinDemo {
	final static String QUERY =
		// Note that the DTO created with NEW here is not a JPA Entity
		// so we have to give its full class name (if it were, we wouldn't).
		// The Join on p.sales will get a List<Sale> entities.
		"SELECT NEW jpa.features.SalesReportDTO(p.lastName, SUM(s.amount)) " + 
		"FROM SalesPerson p LEFT JOIN p.sales s GROUP BY p.lastName";
	
	static EntityManager em;
	
	public static void main(String[] args) {
		em = JpaUtil.getEntityManager();
		System.out.println("Sales Report:");
		doReport();
		System.out.println("-------------");
		System.exit(0);
	}
	
	public static void doReport() {
		int n = 0;
		for (SalesReportDTO data : em.createQuery(QUERY, SalesReportDTO.class).getResultList()) {
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
