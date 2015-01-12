package domain.sales;

import javax.persistence.Entity;

import domain.Person;


@Entity
public class SalesPerson extends Person {
	int amount;	// JPA "sum" can for now only work on ints
}
