package domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import domain.Person;

@Entity
public class Actor extends Person {
	
	@ManyToMany
	Set<VideoRecording> films = new HashSet<VideoRecording>();
	
	public Actor() {
		// javabean constructor
	}
	
	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Set<VideoRecording> getVideos() {
		return films;
	}
	public void setVideos(Set<VideoRecording> films) {
		this.films = films;
	}
}
