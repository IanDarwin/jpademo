package domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *  This class represents a track (ie a song).  This class describes
 *  the track's title and duration.
 *
 *   <pre>
 *     Usage Example:
 *
 *     Duration firstDuration = new Duration(0, 4, 30);   // hr, min, sec
 *     Track firstTrack = new Track("Tooty Fruity", firstDuration);
 *
 *   </pre>
 *
 *  @author 936 Development Team
 */
@Entity
@Table(name="Music_Tracks")
public class Track implements Serializable {

	private static final long serialVersionUID = 7412748348094654728L;

	private int id;
	
	/**
	 *  The track title
	 */
	private String title;

	/**
	 *  The track duration
	 */
	private Duration duration;
	
	/** Recording this track is part of */
	private int recordingId;

	@Column(name="product_id")
	public int getRecordingId() {
		return recordingId;
	}

	public void setRecordingId(int recordingId) {
		this.recordingId = recordingId;
	}

	/**
	 *  Default constructor.  Simply creates an empty track.
	 */
	public Track() {
		title = "empty";
		duration = new Duration();
	}

  	/**
	 *  Creates a Track with the given parameter values
	 */
	public Track(String aTitle, Duration aDuration) {
		title = aTitle;
		duration = aDuration;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}	
	
	
	/**
	 *  Returns the title of the track
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  Sets the title of the track
	 */
	public void setTitle(String	aTitle) {
		title = aTitle;
	}

	/**
	 *  Returns the duration of the track
	 */
	@Embedded
	public Duration getDuration() {
		return duration;
	}

	/**
	 *  Sets the duration of the track
	 */
	public void setDuration(Duration aDuration) {
		duration = aDuration;
	}

	/**
	 *  Returns a string representation of the Track.  It
	 *  includes the track title and running time. <p>
	 *
	 *  The string has the following format:
	 *
	 *   <pre>
	 *    title, duration
	 *   </pre>
	 */
	public String toString() {

		int totalSeconds = duration.getTotalSeconds();
		int min = totalSeconds / 60;
		int secs = totalSeconds % 60;

		String secsStr = "";

		// pad the seconds w/ leading zero if single digit
		if (secs < 10) {
			secsStr = "0";
		}

		secsStr += Integer.toString(secs);

		return title + ",  " + min + ":" + secsStr;
	}
}
