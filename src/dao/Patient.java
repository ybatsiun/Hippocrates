package dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import dao.User;

@Entity
@Table(name = "patients")
public class Patient  implements Serializable {

	@Id
	public String username;
	
	public String password;
	
	public String authority="ROLE_PATIENT";
	
	public int enabled=1;
	
	@Column(name = "firstName")
	public String firstName ;

	@Column(name = "lastName")
	public String lastName ;

	@Column(name = "phoneNumber")
	public int phoneNumber ;

	@Column(name = "email")
	public String email ;

	
	private static final long serialVersionUID = 1L;

	/*
	 * @Id public String username;
	 */

	public String getEmail() {
		return email;
	}

	public String getLastName() {
		return lastName;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public Patient() {
	}

	public Patient(String username, int enabled, String password, String authority, String firstName, String lastName,
			int phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	/*
	 * public Patient(String username,int enabled,String password,String
	 * authority) { super(username, enabled, password, authority); }
	 */

	/*
	 * @Override public String toString() { return "Patient [username" +
	 * username + ",firstName=" + firstName + ", lastName=" + lastName +
	 * ", phoneNumber=" + phoneNumber + ", email=" + email + "]"; }
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
