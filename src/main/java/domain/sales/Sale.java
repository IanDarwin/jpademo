package domain.sales;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Sale entity - track one sale.
 */
@Entity
public class Sale {
	private long id;
	private Customer cust;
	private SalesPerson salesRep;
	private Date salesDate;
	// Sale amount, rounded to nearest dollar
	int amount;
	
	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	public Customer getCustomer() {
		return cust;
	}
	public void setCustomer(Customer cust) {
		this.cust = cust;
	}
	
	@ManyToOne
	public SalesPerson getSalesRep() {
		return salesRep;
	}
	public void setSalesRep(SalesPerson salesRep) {
		this.salesRep = salesRep;
	}
	
	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
