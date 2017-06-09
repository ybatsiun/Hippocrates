package dao;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import validation.ValidSchedule;

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {
	
	
	@Size(min = 5, max = 30, message = "Username must be between 5 and 20 characters")
	public String username;
	
	public int enabled = 1;

	public String authority = "ROLE_DOCTOR";

	@Size(min = 3, max = 30, message = "First name  must be between 3 and 30 characters")
	public String firstName;

	@Size(min = 3, max = 30, message = "Last name must be between 3 and 30 characters")
	public String lastName;

	@Pattern(regexp="0\\d{9}", message="please enter a valid phone number starting from '0' and following by 9 digits")
	public String phoneNumber;

	@NotEmpty(message="please enter your email")
	@Email(message="email does not appear to be valid")
	public String email;

	@NotEmpty(message="please enter  your field")
	public String field;

	@Size(min = 10, max = 20, message = "Password must between 10 and 20 characters")
	public String password;
    @ValidSchedule
	public ArrayList<LocalTime> monday;

	public ArrayList<LocalTime> mondayBlank;

	private static final long serialVersionUID = 1L;

	public List<Calendar> calendar;

	/*
	 * Replaced getters and setters for schedule.If its not working without it
	 * try to make new table special for schedule
	 */
	
	public Doctor() {
		ArrayList<LocalTime> constructor = new ArrayList<LocalTime>();
		for (int a = 8; a < 18; a++) {
			for (int b = 0; b < 60; b += 15) {
	
				constructor.add(LocalTime.of(a, b));
	
			}
		}
		this.setMondayBlank(constructor);
	}

	public Doctor(ArrayList<LocalTime> monday) {
		super();
		this.monday = monday;
	}

	@Override
	public String toString() {
		return "Doctor [username=" + username + ", enabled=" + enabled + ", authority=" + authority + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", field=" + field + ", password=" + password + ", monday=" + monday + "]";
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

	@Transient
	public ArrayList<LocalTime> getMonday() {
		return monday;
	}
	@Transient
	public ArrayList<LocalTime> getMondayBlank() {
		return mondayBlank;
	}

	public void setMondayBlank(ArrayList<LocalTime> mondayBlank) {
		this.mondayBlank = mondayBlank;
	}

	public void setMonday(ArrayList<LocalTime> monday) {
		this.monday = monday;
	}

	@Id
	public String getUsername() {
		return username;
	}

	@OneToMany(targetEntity = Calendar.class, mappedBy = "doctor", fetch = FetchType.EAGER)
	public List<Calendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<Calendar> calendar) {
		this.calendar = calendar;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	

	/*
	 * Replaced getters and setters for schedule.If its not working without it
	 * try to make new table special for schedule
	 */

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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}