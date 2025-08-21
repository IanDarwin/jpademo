package domain;

import jakarta.persistence.Embeddable;

/** 
 * An address that can be embedded.
 * In real life you'd use a shorter name.
 */
@Embeddable
public class PersonEmbeddableAddress {
	String city;
	String province;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
}
