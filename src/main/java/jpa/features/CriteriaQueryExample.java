package jpa.features;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import jpa.JpaUtil;
import domain.Person;

/**
 * JPA's Criteria demands a "MetaModel" class for each
 * Entity used in a Criteria. For one or two you can
 * hand-roll the MetaModel class (see Person_.java in
 * this package); for more, or if classes change a lot,
 * JPA impls provide a MetaModel generator. Hibernate's is:
 * org.hibernate:hibernate-jpamodelgen:5.0.0-Final (or later)
 */
public class CriteriaQueryExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Assume that the user has filled in a form field (Swing or Web, matters not)
		// and given us the following values:
		String firstName = "Ian";
		//String lastName = null;
		//String workPhone = "+44 7910 123456";
		//String homePhone = null;
		// and we want to use each of these fields, where non-null, in the query.
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Person> cq = qb.createQuery(Person.class);
		Root<Person> root = cq.from(Person.class);
		Predicate personPredicate = null;
		ParameterExpression<String> firstNameParameter = null;
		
		if (firstName != null) {
			firstNameParameter = qb.parameter(String.class);
			personPredicate = qb.equal(root.get(Person_.firstName), firstNameParameter);
			cq.where(personPredicate);
		}
		// And similar for lastName...
		
		TypedQuery<Person> query = entityManager.createQuery(cq);
		
		// Can't do these until the Query is finally created...
		if (firstName != null) {
			query.setParameter(firstNameParameter, firstName);
		}
		// And similar for lastName...

		System.out.println("Running the generated Query: " + query);
		for (Person p : query.getResultList()) {
			System.out.println("Found person " + p);
		}
	}

}
