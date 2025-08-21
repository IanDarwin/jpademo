package hibernate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Type2 {
	int id;
	Type1 parent;


	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Type1 getParent() {
		return parent;
	}
	
	public void setParent(Type1 parent) {
		this.parent = parent;
	}
	
	@Override
	public String toString() {
		return "Type2[" + id + "]";
	}
}
