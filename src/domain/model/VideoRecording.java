package domain.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class VideoRecording extends Recording {

	@ManyToMany(mappedBy="films")
	Set<Actor> actors;

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public Duration getDuration() {
		return new Duration(0, 47, 0);
	}
}
