package domain;

import java.io.Serializable;
import java.util.Map;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PType",
	discriminatorType=DiscriminatorType.CHAR)
@DiscriminatorValue(value="P")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	AddressType addressType;
	protected String firstName;
	protected String lastName;
	int version;
	Map<PrefType,Preference> prefs;
	// Used by JPA with SINGLE_TABLE mode; not really
	// part of the data media; just exposed here so that
	// import.sql will work for any inheritance strategy.
	char pType = 'P';
	
	public Person() {
		// empty
	}
	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Enumerated(EnumType.STRING)
	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	@Transient /* synthetic: cannot be used in JPA queries, alas. */
	public String getName() {
		StringBuilder sb = new StringBuilder();
		if (firstName != null)
			sb.append(firstName).append(' ');
		if (lastName != null)
			sb.append(lastName);
		return sb.toString();
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public char getPType() {
		return pType;
	}

	public void setPType(char pType) {
		this.pType = pType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((prefs == null) ? 0 : prefs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (prefs == null) {
			if (other.prefs != null)
				return false;
		} else if (!prefs.equals(other.prefs))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getName() + "#" + getId();
	}

	// XXX This should probably be in Customer
	@OneToMany(mappedBy="person")
	@MapKey(name="prefType")
	public Map<PrefType, Preference> getPrefs() {
		return prefs;
	}

	public void setPrefs(Map<PrefType, Preference> prefs) {
		this.prefs = prefs;
	}
	
}
