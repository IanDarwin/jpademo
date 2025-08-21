package jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import domain.media.Actor;

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
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		renameActorsByLastName(entityManager, "Ford", "Smith");
		int actorHarrison = -103; // just happen to know (import.sql)
		entityManager = JpaUtil.getEntityManager(); // get a new one!
		Actor a = entityManager.find(Actor.class, actorHarrison);
		System.out.println(a);
	}

	private static void renameActorsByLastName(EntityManager entityManager,
		String oldLastName, String newLastName) {

		Query query =
			entityManager.createNamedQuery("updateActorLastNameById");
		query.setParameter(1, newLastName);
		query.setParameter(2, oldLastName);
		entityManager.getTransaction().begin();
		int rowCount = query.executeUpdate();
		entityManager.getTransaction().commit();
		System.out.println(rowCount + " Actors renamed from " + oldLastName + " to " + newLastName);
	}
}
