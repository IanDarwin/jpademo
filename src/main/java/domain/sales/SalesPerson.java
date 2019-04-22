package domain.sales;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import domain.Person;

@Entity
@DiscriminatorValue(value="S")
public class SalesPerson extends Person {

	public SalesPerson() {}
	
	public SalesPerson(String firstName, String lastName) {
		super(firstName, lastName);
	}

	private List<Customer> customers;
	private List<Sale> sales;

	@OneToMany(mappedBy = "salesRep")
	public List<Customer> getCustomers() {
		if (customers == null) {
			return customers = new ArrayList<>();
		}
		return customers;
	}

	public void addCustomer(Customer customer) {
		List<Customer> customers = getCustomers();
		if(customers == null) {
			customers = new ArrayList<>();
		}
		customers.add(customer);
		setCustomers(customers);
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
		// Make sure this salesperson is the reps of the customer
		for (Customer customer : customers) {
			customer.setSalesRep(this);
		}
	}

	@OneToMany(mappedBy = "salesRep")
	public List<Sale> getSales() {
		if (sales == null) {
			return sales = new ArrayList<>();
		}
		return sales;
	}

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}

}
