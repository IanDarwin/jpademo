package jpa.dsd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chapter {
	@Id long id;
	String name;
	String description;
}
