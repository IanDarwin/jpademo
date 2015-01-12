package domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class VideoRecording extends Recording {

	private static final long serialVersionUID = -2887474264317103342L;

	private int year;	
	private Set<Actor> actors = new HashSet<Actor>();
	private Duration duration;

	private String director;
	
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

	@ManyToMany(mappedBy="videos")
	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	
	public void addActor(Actor a) {
		getActors().add(a);
		a.getVideos().add(this);
	}

	@Override
	public Duration getDuration() {
		return duration;
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

	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
}
