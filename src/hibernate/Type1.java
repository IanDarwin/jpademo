package hibernate;

public class Type1 {
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Type1[" + id + "]";
	}
}
