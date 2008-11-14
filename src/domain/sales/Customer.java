package domain.sales;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import domain.Address;
import domain.Person;
/**
 * A Customer is a Person with addresses and a sales rep.
 */
public class Customer extends Person {
	@OneToOne
	protected Address homeAddress;
	@OneToOne
	protected Address workAddress;
	
	// There can be many customers to one sales rep.
	@ManyToOne
	protected SalesPerson rep;
}
