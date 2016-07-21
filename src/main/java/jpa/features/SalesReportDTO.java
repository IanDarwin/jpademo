package jpa.features;

/**
 * The Data Transfer Object for the Sales Report.
 * Note that a DTO does not need to be an @Entity.
 * @author Ian Darwin
 */
public class SalesReportDTO {
	private String name;
	private long amount;
	
	public SalesReportDTO(String name, long amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
