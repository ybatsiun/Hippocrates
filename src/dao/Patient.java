package dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "patients")
public class Patient implements Serializable {
	
	@Size(min = 5, max = 30, message = "Username must be between 5 and 20 characters")
	public String username;
	
	@Size(min = 10, max = 20, message = "Password must between 10 and 20 characters")
	public String password;

	public String authority = "ROLE_PATIENT";

	public int enabled = 1;
	
	@Size(min = 3, max = 30, message = "First name  must be between 3 and 30 characters")
	@Column(name = "firstName")
	public String firstName;
	
	@Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
	@Column(name = "lastName")
	public String lastName;

	@Pattern(regexp="0\\d{9}", message="please enter a valid phone number starting from '0' and following by 9 digits")
	@Column(name = "phoneNumber")
	public String phoneNumber;
	
	@NotEmpty(message="please enter your email")
	@Email(message="email does not appear to be valid")
	@Column(name = "email")
	public String email;

	private static final long serialVersionUID = 1L;

	/*
	 * @Id public String username;
	 */
	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public Patient() {
	}

	public Patient(String username, String password, String firstName, String lastName,
			String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
