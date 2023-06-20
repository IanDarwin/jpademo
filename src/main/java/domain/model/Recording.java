package domain.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="RType",
	discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue(value="R")
public abstract class Recording implements Serializable {

	private static final long serialVersionUID = 5895997814851620471L;
	private int id;
	protected String title;
	protected double price;
	int version;
	
	public Recording() {
		version = 0;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@OneToOne
	public abstract Duration getDuration();
	public void setDuration(Duration d) {
		// System.err.println("Lame-but-required method called");
	}
		
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title;
	}
}
