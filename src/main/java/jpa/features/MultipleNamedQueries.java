package jpa.features;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

/** A dummy Entity just to show adding NamedQueries
 * to an Entity class.
 */
@NamedQueries({
	@NamedQuery(name="findActorByName",
			query="select a from Actor a where a.firstName = ?1"),
	@NamedQuery(name="findActorbyLastName",
			query="select a from Actor a where a.lastName  = ?1")
})
@Entity // Can only put named queries on an Entity.
public class MultipleNamedQueries {
	// There should be some code here
	int id;

	@Id public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
