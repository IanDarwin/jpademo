package domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Simple JPA example of discriminator columns
 */
@Entity
@DiscriminatorValue(value="B")
public class HierBottom extends HierTop {
	
}
