package jpa.features;

/**
 * The Data Transfer Object for the Sales Report.
 * Note that a DTO does not need to be an @Entity.
 * @author Ian Darwin
 */
public class SalesReportDTO {
	private String name;
	private int amount;
	
	public SalesReportDTO(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
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
