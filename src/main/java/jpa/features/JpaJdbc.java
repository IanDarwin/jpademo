package jpa.features;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import jpa.JpaUtil;

import org.hibernate.Session;

import domain.misc.Fish;
import domain.misc.FishPK;

/**
 * Demonstrates using the Hibernate Session to perform "raw" JDBC ops
 * but will only work if Hibernate is the underlying JPA provider.
 */
public class JpaJdbc {
	public static void main(String[] args) throws SQLException {
		EntityManager em = JpaUtil.getEntityManager();
		Fish goldie = new Fish();
		goldie.setFishName("Goldie");
		FishPK pk = new FishPK(100, 100);
		goldie.setId(pk);
		em.getTransaction().begin();
		em.persist(goldie);
		em.getTransaction().commit();
		em.close();
		
		em = JpaUtil.getEntityManager();
		System.out.println("Using EM: " + em.getClass().getName());
		Connection conn = null;
		try {
			Class<?> clazz = Session.class;
			conn = em.unwrap(Session.class).connection();
		}  catch (PersistenceException pex) {
			System.err.println("Sorry, EntityManager " + em + " does not support " + clazz);
			System.err.println("Try using Hibernate as your EntityManager next time.");
			System.exit(1);
		}
		PreparedStatement st = conn.prepareStatement("select * from fish");
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			String name = rs.getString("fishname");
			int species = rs.getInt("speciesId");
			int individ = rs.getInt("individualId");
			System.out.printf("Name %s, species %d, individual %d%n",
					name, species, individ);
		}
	}
}
