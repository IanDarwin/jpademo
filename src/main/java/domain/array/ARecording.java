package domain.array;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ARecording implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * @param title
	 * @param tracks
	 */
	public ARecording(String title, ATrack[] tracks) {
		super();
		this.title = title;
		this.tracks = tracks;
	}
	
	public ARecording(String title) {
		this(title, new ATrack[0]);
	}
	
	public ARecording() {
		// Empty, required for JPA conformance.
	}

	long id;
	String title;
	ATrack[] tracks;
	
	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	// You have to comment out this @OneToMany to get EclipseLink to run any demos!
	//@OneToMany(targetEntity=ATrack.class) @IndexColumn(name="trackNum")
	public ATrack[] getTracks() {
		return tracks;
	}

	public void setTracks(ATrack[] tracks) {
		this.tracks = tracks;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTitle()).append("(");
		for (ATrack t : getTracks()) {
			sb.append(t.getName()).append(",");
		}
		sb.append(")");
		return sb.toString();
	}
}
