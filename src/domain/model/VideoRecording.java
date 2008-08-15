package domain.model;

import java.util.Set;

import javax.persistence.ManyToMany;

public class VideoRecording extends Recording {
	@ManyToMany(mappedBy="actor_key")
	Set<Actor> actors;

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
}
