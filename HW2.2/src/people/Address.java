package people;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class Address {

	private String streetName;
	private String streetType;
	private int zipCode;

	public Address(String streetName, String streetType, int zipCode) {
		this.streetName = streetName;
		this.streetType = streetType;
		this.zipCode = zipCode;
	}

	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetType() {
		return streetType;
	}
	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

}
