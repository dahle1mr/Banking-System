package people;
import java.util.*;

import accounts.Account;
/**
 * @author Michael Dahle - Code Solution for Homework Assignment 2
 */

public class User {

	private String firstName;
	private String lastName;
	private String licenseNumber;
	private String occupation;
	private Address address;
	private int birthYear;
	private ArrayList<Account> accountList;


	public User(String firstName, String lastName, String licenseNumber, String occupation, String streetName, String streetType, int zipCode, int birthYear, ArrayList<Account> accountList) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.licenseNumber = licenseNumber;
		this.occupation = occupation;
		this.address = new Address(streetName, streetType, zipCode);
		this.birthYear = birthYear;
		this.accountList = accountList;

	}

	public String getFirstName() {
		return firstName;
	}

	public String setFirstName(String firstName) {
		this.firstName = firstName;
		return firstName;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(ArrayList<Account> accountList) {
		this.accountList = accountList;
	}

	public int getAge() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year - this.birthYear;

	}
	public String toString() {
		return firstName + "," + lastName + "," + licenseNumber + "," + occupation + "," + this.address.getStreetName() + "," + this.address.getStreetType() + "," + this.address.getZipCode() + "," + birthYear;
	}

}

