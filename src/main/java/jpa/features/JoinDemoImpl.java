package jpa.features;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;

import jpa.JpaUtil;

/** 
 * An example of a Join and also an example of a DTO creation
 * @author Ian Darwin
 */
public class JoinDemoImpl {
	static String query =
			// Note that the DTO created with NEW here is not a JPA Entity!
			"SELECT NEW JoinDemoImpl(p.lastName, SUM(s.amount))" + 
			"FROM SalesPerson p LEFT JOIN p.sales s GROUP BY p.lastName";
	String name;
	int amount;
	private long id;
	static EntityManager em;
	
	public static void main(String[] args) {
		em = JpaUtil.getEntityManager();
		sendReport();
	}
	@SuppressWarnings("unchecked")
	public static void sendReport() {
		Query q = em.createQuery(query);
		final List<JoinDemoImpl> resultList = q.getResultList();
		for (JoinDemoImpl data : resultList) {
			System.out.printf("Name %s, Amount %s%n", data.name, data.amount);
		}
	}
	
	// The rest of this is the DTO portion,
	// could be its own class (but must be a public
	// class for JPA to treat it as a DTO).
	public JoinDemoImpl(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	@Id public long getId() {
		return this.id;
	}
	private void setId(long id) {
		this.id = id;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
