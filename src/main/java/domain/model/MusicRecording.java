package domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 *  This class represents a music recording.  It contains additional
 *  data members for the artist name and a list of tracks (ie songs).
 *
 *   <pre>
 *     Usage Example:
 *        MusicRecording myRecording = new MusicRecording("John Lennon", myTrackList, "Double Fantasy",
 *   </pre>
 *
 *  @author 936 Development Team
 *  @author Ian Darwin modifications for standalone demo.
 */

@Entity
@Table(name="MusicRecordings")
@DiscriminatorValue(value="M")
public class MusicRecording extends Recording {

	private static final long serialVersionUID = -2657285648284489986L;

	/**
	 *  The name of the artist/band
	 */
	private String artist;

	/**
	 *  The list of tracks/songs
	 */
	private Track tracks[] = new Track[0];

	/**
	 *  The recording category
	 */
	private String category;


	/** The cover image, or null */
	private String imageName;

	/**
	 *  Default constructor
	 */
	public MusicRecording() {
		// set the data members using the setter methods
	}

	/**
	 * Creates a MusicRecording object with given parameter values
	 * Too complex; should use Builder pattern here.
	 */
	public MusicRecording(String theArtist, Track[] theTrackList,
						  String theTitle, double thePrice,
						  String theCategory, String theImageName) {

		super.title = theTitle;
		super.price = thePrice;
		this.category = theCategory;
		artist = theArtist;
		tracks = theTrackList;
	}

	/**
	 *  Creates a MusicRecording object with given parameter values
	 *
	 */
	public MusicRecording(String theArtist,
						  String theTitle, double thePrice,
						  String theCategory, String theImageName) {

		this(theArtist, null, theTitle, thePrice, theCategory, theImageName);
	}

	@Override
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(title).append(" - ");
        buffer.append(artist).append(" (");
        buffer.append(tracks.length).append(" tracks)");
        return buffer.toString();
    }

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 *  Returns the artist name
	 */
	@Column(name="artist_name")
	public String getArtist() {
		return artist;
	}

	/**
	 *  Sets the artist name
	 */
	public void setArtist(String theArtist) {
		artist = theArtist;
	}

	/**
	 *  Returns the recording duration.
	 *  Overrides the method from Recording.
	 *
	 *  Iterates over the list of tracks and keeps a running
	 *  total of each track's duration.
	 */
	@Transient @Override
	public Duration getDuration() {

		if (tracks == null) {
			return new Duration(0);
		}

		int total = 0;
		for (int i=0; i < getTracks().size(); i++) {
			total += tracks[i].getDuration().getTotalSeconds();
		}

		return new Duration(total);
	}

	/**
	 *  Allow us to sort the recordings by artist name
	 */
	public int compareTo(Object object) {

		MusicRecording recording = (MusicRecording) object;
		String targetArtist = recording.getArtist();

		return artist.compareTo(targetArtist);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@OneToMany(mappedBy="recording", cascade=CascadeType.ALL)
	@OrderColumn(name="index_number")
	public List<Track> getTracks() {
		if (tracks == null || tracks.length == 0) {
			return new ArrayList<Track>();
		}
		return Arrays.asList(tracks);
	}

	public void setTracks(List<Track> tracks) {
		Track[] target = new Track[tracks.size()];
		this.tracks = (Track[]) tracks.toArray(target);
	}
}
