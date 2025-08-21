package domain.sales;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import domain.Address;
import domain.Person;

/**
 * A Customer is a Person with addresses and a sales rep.
 */
@Entity
@DiscriminatorValue(value="C")
public class Customer extends Person {
	private static final long serialVersionUID = -6014208311502015874L;
	protected Address homeAddress = new Address();
	protected Address workAddress = new Address();
	
	// There can be many customers to one sales rep.
	protected SalesPerson salesRep;
	
	public Customer() {
		// empty
	}
	
	public Customer(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@OneToOne
	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	@ManyToOne
	public SalesPerson getSalesRep() {
		return salesRep;
	}

	public void setSalesRep(SalesPerson rep) {
		this.salesRep = rep;
		if(rep!=null && !rep.getCustomers().contains(this)) {
			rep.addCustomer(this);
		}
	}

	@OneToOne
	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}
}
