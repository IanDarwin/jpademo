package domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value="V")
public class VideoRecording extends Recording {

	private static final long serialVersionUID = -2887474264317103342L;

	String producer;
	String rating;
	String title;
	double price;
	MusicCategory category;
	String imageName;
	private int year;
	private List<Actor> actors = new ArrayList<>();
	private Duration duration;
	private String director;

	public VideoRecording() {
		// empty constructor
	}
	
	public VideoRecording(String title) {
		this(title, 0);
	}
	
	public VideoRecording(String title, int year) {
		super();
		setTitle(title);
		setYear(year);
	}

	public VideoRecording(String producer, List<Actor> actorList, String rating, int yearReleased, String title,
						  double price, MusicCategory category, String imageName, Duration duration) {
		this.producer = producer;
		this.actors.addAll(actorList);
		this.rating = rating;
		this.year = yearReleased;
		this.title = title;
		this.price = price;
		this.category = category;
		this.imageName = imageName;
		this.duration = duration;
	}

	@ManyToMany(mappedBy="videos")
	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
	
	/** Make relationship bidirectional */
	public void addActor(Actor a) {
		this.getActors().add(a);
		a.getVideos().add(this);
	}

	public MusicCategory getCategory() {
		return category;
	}
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
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

	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	public int getYearReleased() {
		return year;
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
