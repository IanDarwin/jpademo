package domain;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Simple JPA example of discriminator columns
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TypeField",
		discriminatorType=DiscriminatorType.CHAR)
// This Value should not be needed since the class is abstract;
// however Hibernate blows up if it is missing; Eclipselink doesn't care.
@DiscriminatorValue(value="T")
public abstract class HierTop {
	int id;

	@Id  @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
