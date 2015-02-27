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

	int amount; // JPA "sum" can for now only work on ints
	private List<Customer> customers;

	@OneToMany(mappedBy = "salesRep", cascade = CascadeType.ALL)
	public List<Customer> getCustomers() {
		if (customers == null || customers.size() == 0) {
			return new ArrayList<Customer>();
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
