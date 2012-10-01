package domain.misc;

import java.io.Serializable;

import javax.persistence.Embeddable;

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

}
