package domain.array;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ATrack implements Serializable {

	private static final long serialVersionUID = 1L;
	
	long id;
	String name;
	
	public ATrack(String name) {
		this.name = name;
	}
	
	public ATrack() {
		// Empty, required for JPA conformance.
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
