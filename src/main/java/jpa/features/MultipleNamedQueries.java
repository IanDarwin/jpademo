package jpa.features;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/** A dummy Entity just to show adding NamedQueries
 * to an Entity class.
 */
@NamedQueries({
	@NamedQuery(name="findActorByName",
			query="select a from Actor a where a.firstName = ?1"),
	@NamedQuery(name="updateActorLastNameById",
			query="UPDATE Actor a SET a.lastName=?2 where a.id=?1")
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
