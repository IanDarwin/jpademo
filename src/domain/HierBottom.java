package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Simple JPA example of discriminator columns
 */
@Entity
@DiscriminatorValue(value="B")
public class HierBottom extends HierTop {
	
}
