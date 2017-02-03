package jpa.features;

/** This is a Data Transfer Object (DTO);
 * it need not be annotated as an Entity,
 * nor listed in the class mappings.
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
