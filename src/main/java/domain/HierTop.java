package domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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

	@Id 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
