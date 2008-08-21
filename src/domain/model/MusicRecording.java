package domain.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
// to the database for this to work; JPA/Hibernate needs
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
	 *	The recording id
	 */
	private int id;

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

	@Transient
	private final NumberFormat currencyFormatter = DecimalFormat.getCurrencyInstance();

	@Override
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("MusicRecording {\n");
        buffer.append("\t" + artist + "\n");
        buffer.append("\t" + title + "\n");
		buffer.append("\t" + currencyFormatter.format(price) + "\n");
        buffer.append("\tTracks {\n");
        if (tracks != null)
        for (Track track : tracks) {
        	if (track == null) {
        		System.err.println("NULL TRACK");
        	} else 
        	if (track.getTitle() != null)
            buffer.append("\t\t" + track.getTitle() + "\n");
        }
        buffer.append("\t}\n");
        buffer.append("}");
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

	@Transient
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


	/**
	 *  Returns the recording id.
	 */
	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}


	/**
	 *  Sets the recording id
	 */
	void setId(int id) {
		this.id = id;
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
	public Track[] getTracks() {
		return tracks;
	}


	public void setTracks(Track[] tracks) {
		this.tracks = tracks;
	}
}
