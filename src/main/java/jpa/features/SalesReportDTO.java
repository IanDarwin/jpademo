package jpa.features;

/**
 * The Data Transfer Object for the Sales Report.
 * Note that a DTO does not need to be an @Entity.
 * @author Ian Darwin
 */
public class SalesReportDTO {
	private String name;
	private int amount;
	private long id;
	
	public SalesReportDTO(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	public long getId() {
		return this.id;
	}
	@SuppressWarnings("unused") // For JPA
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
