package domain.media;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *  This class describes a duration of time.  It contains
 *  the hour, minutes and seconds.
 *
 *  <pre>
 *    Usage Example:
 *
 *      Duration lunchDuration = new Duration(1, 0, 0); // 1 hr, 0 mins, 0 secs
 *      System.out.println(lunchDuration);
 *
 *      Duration breakDuration = new Duration(0, 15, 30); // 0 hrs, 15 mins, 30 secs
 *      System.out.println(breakDuration);
 *
 *      Duration labDuration = new Duration(2700); // 2700 seconds = 45 minutes
 *      System.out.println(labDuration);
 *  </pre>
 * @author 471 Development Team
 * @author Ian Darwin
 */

//@Embeddable
@Entity
public class Duration implements Serializable {

	private static final long serialVersionUID = -6020955281760590352L;

	/**
	 * The ID property (if not using Embeddable).
	 */
	private long id;
	
	/**
	 *  The number of hours
	 */
	private int hours;

	/**
	 *  The number of minutes
	 */
	private int minutes;


	/**
	 *  The number of seconds
	 */
	private int seconds;

	/**
	 *  Creates a Duration object with 0 hours, minutes and seconds.
	 */
	public Duration() {
		this(0, 0, 0);
	}

	/**
	 * Creates a Duration object with given parameter values
	 * @param theHours The number of hours
	 * @param theMinutes The number of Minutes
	 * @param theSeconds The number of Seconds
	 */
	public Duration(int theHours, int theMinutes, int theSeconds) {
		hours = theHours;
		minutes = theMinutes;
		seconds = theSeconds;
	}

	/**
	 * Creates a Duration object with given parameter values
	 * @param totalSeconds The number of seconds.
	 */
	public Duration(int totalSeconds) {
		hours = totalSeconds / 3600;
		int rem = totalSeconds - (hours * 3600);
		minutes = rem / 60;
		seconds = rem % 60;
	}
	
	/**
 	 * @return the primary key
	 */
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	/**
	 * Set the identifier
	 * @param id The primary key
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @returns the hours portion of the duration
	 */	
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @returns the minutes portion of the duration
	 */
	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 *  Returns the seconds portion of the duration.
	 *  <p/>
	 *  Note:  This is <b>NOT</b> the total seconds.
	 *  @see #getTotalSeconds()
	 */
	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	/**
	 *  Returns the total seconds
	 */
	public int getTotalSeconds() {
		return seconds + (60 * (minutes + (60 * hours)));
	}
	
	/**
	 * Set the total seconds; we'll convert to H:M:S for you.
	 * @param seconds
	 */
	public void setTotalSeconds(int totalSeconds) {
		hours = totalSeconds / 3600;
		int rem = totalSeconds - (hours * 3600);
		minutes = rem / 60;
		seconds = rem % 60;
	}

	/**
	 *  Returns a <b>new</b> duration object that is the sum of the
	 *  supplied Duration object and current object
	 */
	public Duration add(Duration someTime) {
		int total = getTotalSeconds() + someTime.getTotalSeconds();
		return new Duration(total);
	}

	/**
	 *  Returns a <b>new</b> duration object that is the difference of the
	 *  supplied Duration and current object.  The difference returned is
	 *  the absolute difference, so the duration will always be positive.
	 */
	public Duration subtract(Duration someTime) {
		int diff = getTotalSeconds() - someTime.getTotalSeconds();
		int total = Math.abs(diff);

		return new Duration(total);
	}


	/**
	 *  Returns a string representation of the Duration object: <br>
	 *
	 *	<pre>
	 *
	 *  Format
	 *    hh:mm:ss
	 *
	 *  </pre>
	 */
	public String toString() {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
}
