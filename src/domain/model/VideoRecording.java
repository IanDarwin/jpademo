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
		super();
		setTitle(title);
	}
	
	@ManyToMany(mappedBy="films")
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
}
