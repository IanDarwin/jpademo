package jpa.features;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import domain.misc.Fish;
import domain.misc.FishPK;

/**
 * Demonstrates using the Hibernate Session to perform "raw" JDBC ops
 * but will only work if Hibernate is the underlying JPA provider.
 * The notion of mixing JDBC with JPA is a *really bad* idea; you are probably
 * better off using the NativeQuery mechanism if possible.
 */
public class JpaJdbc {
	public static void main(String[] args) throws SQLException {
		EntityManagerFactory entityMgrFactory = Persistence.createEntityManagerFactory("jpademo");
		EntityManager em = entityMgrFactory.createEntityManager();
		Fish goldie = new Fish();
		goldie.setFishName("Goldie");
		FishPK pk = new FishPK(100, 100);
		goldie.setId(pk);
		em.getTransaction().begin();
		em.persist(goldie);
		em.getTransaction().commit();
		em.close();
		
		em = entityMgrFactory.createEntityManager();
		System.out.println("Using EM: " + em.getClass().getName());
		Connection conn = null;
		try {
			conn = em.unwrap(Connection.class);
		}  catch (PersistenceException pex) {
			System.err.println("Sorry, EntityManager " + em + " does not support " + "Connection");
			System.err.println("Try using Hibernate as your EntityManager next time.");
			System.exit(1);
		}
		if (conn == null) {
			System.err.println("EM gave us a NULL JDBC Connection, sorry!");
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
