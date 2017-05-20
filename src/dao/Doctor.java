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

@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {

	public String username;

	public int enabled = 1;

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String authority = "ROLE_DOCTOR";

	public String firstName;

	@Override
	public String toString() {
		return "Doctor [username=" + username + ", enabled=" + enabled + ", authority=" + authority + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", field=" + field + ", password=" + password + ", monday=" + monday + "]";
	}

	public Doctor(ArrayList<LocalTime> monday) {
		super();
		this.monday = monday;
	}

	public String lastName;

	public int phoneNumber;

	public String email;

	public String field;

	public String password;

	private static final long serialVersionUID = 1L;

	public List<Calendar> calendar;



	public ArrayList<LocalTime> monday;

	@Transient
	public ArrayList<LocalTime> getMonday() {
		return monday;
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

	public int getEnabled() {
		return enabled;
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

	public Doctor() {
		super();
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