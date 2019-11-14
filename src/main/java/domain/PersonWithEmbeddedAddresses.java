package domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * When embedding an object, there can be no name clashes, or it will blow up.
 * So we re-map the property names to make them distinct
 */
@Entity
public class PersonWithEmbeddedAddresses {
	@Id @GeneratedValue private int personId;
	@Basic private String name;

	@AttributeOverrides({
		@AttributeOverride(name="city", column= @Column(name="homeCity")),
		@AttributeOverride(name="province", column= @Column(name="homeProvince"))
	})
	@Embedded private PersonEmbeddableAddress homeLocation;

	@AttributeOverrides({
		@AttributeOverride(name="city", column= @Column(name="workCity")),
		@AttributeOverride(name="province", column= @Column(name="workProvince"))
	})
	@Embedded private PersonEmbeddableAddress workLocation;
	
}

