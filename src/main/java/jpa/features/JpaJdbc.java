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
		Connection conn = em.unwrap(Session.class).connection();
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
