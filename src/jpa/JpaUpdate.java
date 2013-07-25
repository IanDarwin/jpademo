package jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import domain.model.Actor;

/**
 * A contrived example of a JPA "UPDATE" statement.
 * If you only wanted to update one actor, you should
 * just find the object, call its set method, and commit
 * the transaction!
 * It is only intended to show the syntax of using the update statement.
 * @author Ian Darwin
 */
public class JpaUpdate {

	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		Query query = entityManager.createNamedQuery("updateActorLastNameById");
		query.setParameter(1, "Ford"); // pkey
		String newLastName = "Smith";
		entityManager.getTransaction().begin();
		query.setParameter(2, newLastName);
		int rowCount = query.executeUpdate();
		System.out.println(rowCount + " actors renamed to " + newLastName);
		
		Actor a = entityManager.find(Actor.class, -103);
		entityManager.getTransaction().commit();
		System.out.println(a);
	}

}
