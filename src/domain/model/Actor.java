package domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import domain.Person;

@Entity
public class Actor extends Person {
	@Column(name="videos")
	Set<VideoRecording> films;
}
