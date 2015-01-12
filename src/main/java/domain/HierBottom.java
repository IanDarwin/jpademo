package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Simple JPA example of discriminator columns
 */
@Entity
@DiscriminatorValue(value="B")
public class HierBottom extends HierTop {
	
}
