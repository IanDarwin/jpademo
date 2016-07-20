package jpa.features;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import jpa.JpaUtil;

/** 
 * An example of a Join and also an example of a DTO creation
 * @author Ian Darwin
 */
public class JoinDemoImpl {
	static String query =
		// Note that the DTO created with NEW here is not a JPA Entity!
		// The Join on p.sales will get a List<Sale> entities.
		"SELECT NEW SalesReportDTO(p.lastName, SUM(s.amount))" + 
		"FROM SalesPerson p LEFT JOIN p.sales s GROUP BY p.lastName";
	
	static EntityManager em;
	
	public static void main(String[] args) {
		em = JpaUtil.getEntityManager();
		sendReport();
	}
	@SuppressWarnings("unchecked")
	public static void sendReport() {
		Query q = em.createQuery(query);
		final List<SalesReportDTO> resultList = q.getResultList();
		for (SalesReportDTO data : resultList) {
			System.out.printf("Name %s, Amount %s%n", data.getName(), data.getAmount());
		}
	}
}
