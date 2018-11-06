package domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import domain.Person;

@Entity
@DiscriminatorValue(value="A")
@NamedQuery(name="updateActorLastNameById",
	query="Update Actor a set a.lastName = ?1 where a.lastName = ?2")
public class Actor extends Person {
	
	Set<VideoRecording> videos = new HashSet<VideoRecording>();
	
	public Actor() {
		// javabean constructor
	}
	
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@ManyToMany
	public Set<VideoRecording> getVideos() {
		return videos;
	}
	public void setVideos(Set<VideoRecording> films) {
		this.videos = films;
	}
	
	/** Make relationship bidirectional */
	public void addVideo(VideoRecording v) {
		this.getVideos().add(v);
		v.getActors().add(this);
	}
}
