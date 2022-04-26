package jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import domain.Address;
import domain.HierBottom;
import domain.Person;
import domain.sales.Customer;
import domain.sales.SalesPerson;

/**
 * Very basic JPA Demo.
 */
public class JpaBasic {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		System.out.println("JpaBasic.main()");

		// IRL you'd have the EntityManager injected  by JakartaEE or by Spring
		EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
		EntityManager entityManager = JpaUtil.getEntityManager();

		try {
			entityManager.createQuery("from Person p order by p.lastName").getResultList().forEach(System.out::println);
		} finally {
			entityManager.close();
			entityManagerFactory.close();
		}
	}

}
