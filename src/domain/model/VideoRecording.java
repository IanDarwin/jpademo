package domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class VideoRecording extends Recording {

	private static final long serialVersionUID = -2887474264317103342L;

	public VideoRecording() {
		// javabean constructor
	}
	
	public VideoRecording(String title) {
		this(title, 0);
	}
	
	public VideoRecording(String title, int year) {
		super();
		setTitle(title);
		setYear(year);
	}
	
	private int year;
	
	@ManyToMany(mappedBy="videos")
	Set<Actor> actors = new HashSet<Actor>();

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	
	public void addActor(Actor a) {
		actors.add(a);
		a.getVideos().add(this);
	}

	@Override
	public Duration getDuration() {
		return new Duration(0, 47, 0);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTitle());
		if (year != 0) {
			sb.append(" (").append(year).append(")");
		}
		return sb.toString();
	}
}
