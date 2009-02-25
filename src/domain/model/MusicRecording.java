package domain.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.IndexColumn;

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

// XXX TODO Warning - you must make some little tweaks
// to the 936 database for this to work; JPA/Hibernate needs
// an index number for the elements in the Track array:
// alter table music_tracks add column index_number integer;
// update music_tracks set index_number = id - 9030 where product_id = 3500;
// update music_tracks set index_number = id - 12045 where product_id = 3667;

@Entity
@Table(name="Music_Recordings")
public class MusicRecording extends Recording {

	private static final long serialVersionUID = -2657285648284489986L;

	/**
	 *  The name of the artist/band
	 */
	private String artist;

	/**
	 *  The list of tracks/songs
	 */
	private Track tracks[];

	/**
	 *  The recording title
	 */
	private String title;

	/**
	 *  The recording price
	 */
	private double price;

	/**
	 *  The recording category
	 */
	private String category;

	/**
	 *  Default constructor
	 */
	public MusicRecording() {
		// set the data members using the setter methods
	}


	/**
	 *  Creates a MusicRecording object with given parameter values
	 *
	 */
	public MusicRecording(String theArtist, Track[] theTrackList,
						  String theTitle, double thePrice,
						  String theCategory, String theImageName) {

		this.title = theTitle;
		this.price = thePrice;
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
		for (int i=0; i < tracks.length; i++) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@OneToMany(mappedBy="recordingId", cascade=CascadeType.ALL)
	@IndexColumn(name="index_number")
	public List<Track> getTracks() {
		return Arrays.asList(tracks);
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = (Track[]) tracks.toArray();
	}
}
