package dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "patients")
public class Patient  implements Serializable {

	private String firstName;

	private String lastName;

	private int phoneNumber;
	
	private String email;
	
	@Id
	private String username;

	public Patient() {
	}

	private static final long serialVersionUID = 1L;

	public Patient(String username, String firstName, String lastName,
			int phoneNumber, String email) {
		/*super(username, enabled, authority);*/
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Patient [username" + username + ",firstName=" + firstName + ", lastName="
				+ lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
