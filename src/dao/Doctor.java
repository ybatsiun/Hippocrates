package dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="doctors")
public class Doctor  implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	public String username;
	public String authority="ROLE_DOCTOR";
	public int enabled=1;
	public String firstName;
	public String lastName;
	public int phoneNumber;
	public String email;
	public String field;
	public String password;
	
	
	
	
	public Doctor() {
		super();
	}
	
	
	public Doctor(String username, String authority, int enabled, String firstName, String lastName, int phoneNumber,
			String email, String field, String password) {
		this.username = username;
		this.authority = authority;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.field = field;
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
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
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
}
