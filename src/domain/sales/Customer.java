package domain.sales;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import domain.Address;
import domain.Person;

/**
 * A Customer is a Person with addresses and a sales rep.
 */
@Entity
public class Customer extends Person {

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
	}

	@OneToOne
	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}
}
