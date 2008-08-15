package features;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="query1",
			query="from Actor a where A.name = ?1"),
	@NamedQuery(name="update2",
			query="UPDATE Customer c SET c.status=0 where c.id=?1"),
})
public class MultipleNamedQueries {
	// There should be some code here
	@Id int id;
}
