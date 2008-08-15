package domain.model;

import java.util.Set;

import javax.persistence.Entity;

import domain.Person;


@Entity
public class Actor extends Person {
	Set<VideoRecording> films;
}
