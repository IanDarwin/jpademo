package domain.misc;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

/** Represents the composite primary key for the pet fish.
 * Note that an Embeddable PK class *must* implement Serializable!
 * @author Ian Darwin
 */
@Embeddable
public class FishPK implements Serializable {

	private static final long serialVersionUID = 7053411383886674157L;
	
	int speciesId;
	int individualId;
	
	public FishPK() {
		// empty
	}
	
	public FishPK(int species, int indiv) {
		setSpeciesId(species);
		setIndividualId(indiv);
	}
	
	public int getSpeciesId() {
		return speciesId;
	}
	public void setSpeciesId(int speciesId) {
		this.speciesId = speciesId;
	}
	
	public int getIndividualId() {
		return individualId;
	}
	public void setIndividualId(int individualId) {
		this.individualId = individualId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + individualId;
		result = prime * result + speciesId;
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
		FishPK other = (FishPK) obj;
		if (individualId != other.individualId)
			return false;
		if (speciesId != other.speciesId)
			return false;
		return true;
	}

}
