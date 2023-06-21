package domain.media;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import domain.Person;

@Entity
@DiscriminatorValue(value="A")
@NamedQuery(name="updateActorLastNameById",
	query="Update Actor a set a.lastName = ?1 where a.lastName = ?2")
public class Actor extends Person {
	private static final long serialVersionUID = 1L;

	Set<VideoRecording> videos = new HashSet<VideoRecording>();

	public Actor() {
		// javabean constructor
	}
	
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Actor(String fullName) {
		// XXX split on last space
		this.firstName = fullName;
	}

	@ManyToMany
	// Describe the naming for the join table; 
	// must agree with the load sql script
	@JoinTable(name="Actor_VideoRecording",
		joinColumns=@JoinColumn(name = "actor_id"),
		inverseJoinColumns=@JoinColumn(name = "video_id"))
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
