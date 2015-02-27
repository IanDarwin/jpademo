package jpa.features;

/** This is a Data Transfer Object (DTO);
 * it must NOT be annotated as an Entity.
 * @author Ian Darwin
 */
public class VideoDto {
	String title;
	double price;

	public VideoDto(String title, double price) {
		super();
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
