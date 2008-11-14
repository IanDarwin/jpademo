package features;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name="findActorByName",
			query="from Actor a where A.name = ?1"),
	@NamedQuery(name="updateActorLastNameById",
			query="UPDATE Actor a SET a.lastName=?2 where a.id=?1"),
})
@Entity // Can only put named queries on an Entity.
public class MultipleNamedQueries {
	// There should be some code here
	@Id int id;
}
