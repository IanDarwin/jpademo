package jpa.dsd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chapter {
	public Chapter() {
		System.out.println("Chapter::init()");
	}
	@Id long id;
	String name;
	String description;
}
