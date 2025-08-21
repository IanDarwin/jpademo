package jpa.features;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

import jpa.JpaUtil;

@Entity
@Table(name="MultiTableEntity")
@SecondaryTable(name="SecondaryTable")
public class MultiTableEntity {

	private int id;
	/** These fields go into the main table */
	private String mainField1, mainfield2;
	/** These fields go into the secondary table (see accessors) */
	private String secoField1, secoField2;
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		MultiTableEntity entity = new MultiTableEntity();
		entity.mainField1 = "one";
		entity.mainfield2 = "two";
		entity.secoField1 = "three";
		entity.secoField2 = "four";
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
		System.out.println("Created entity " + entity.getId());
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMainField1() {
		return mainField1;
	}
	public void setMainField1(String mainField1) {
		this.mainField1 = mainField1;
	}
	public String getMainfield2() {
		return mainfield2;
	}
	public void setMainfield2(String mainfield2) {
		this.mainfield2 = mainfield2;
	}
	@Column(table="SecondaryTable")
	public String getSecoField1() {
		return secoField1;
	}
	public void setSecoField1(String secoField1) {
		this.secoField1 = secoField1;
	}
	@Column(table="SecondaryTable")
	public String getSecoField2() {
		return secoField2;
	}
	public void setSecoField2(String secoField2) {
		this.secoField2 = secoField2;
	}	
}
