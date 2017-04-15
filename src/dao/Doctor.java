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
	
	/**/
	
	public ArrayList <LocalTime> monday;
	@Transient
	public ArrayList<LocalTime> getMonday() {
		return monday;
	}
	public void setMonday(ArrayList<LocalTime> monday) {
		this.monday = monday;
	}

/*	public ArrayList <Calendar> tuesday;
	public ArrayList <Calendar> wednesday;
	public ArrayList <Calendar> thursday;
	public ArrayList <Calendar> friday;*/
	


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

	public boolean mn_10;
	public boolean mn_11;
	public boolean mn_12;

	public String mn_10_text;
	public String mn_11_text;
	public String mn_12_text;
	public boolean mn_10_isbusy;
	public boolean mn_11_isbusy;
	public boolean mn_12_isbusy;
	public String mn_10_username;
	public String mn_11_username;
	public String mn_12_username;

	public String mn_10_firstname;
	public String mn_11_firstname;
	public String mn_12_firstname;

	public String mn_10_lastname;
	public String mn_11_lastname;
	public String mn_12_lastname;

	public int mn_10_phonenumber;
	public int mn_11_phonenumber;
	public int mn_12_phonenumber;

	public String getmn_10_firstname() {
		return mn_10_firstname;
	}

	public void setmn_10_firstname(String mn_10_firstname) {
		this.mn_10_firstname = mn_10_firstname;
	}

	public String getmn_11_firstname() {
		return mn_11_firstname;
	}

	public void setmn_11_firstname(String mn_11_firstname) {
		this.mn_11_firstname = mn_11_firstname;
	}

	public String getmn_12_firstname() {
		return mn_12_firstname;
	}

	public void setmn_12_firstname(String mn_12_firstname) {
		this.mn_12_firstname = mn_12_firstname;
	}

	public String getmn_10_lastname() {
		return mn_10_lastname;
	}

	public void setmn_10_lastname(String mn_10_lastname) {
		this.mn_10_lastname = mn_10_lastname;
	}

	public String getmn_11_lastname() {
		return mn_11_lastname;
	}

	public void setmn_11_lastname(String mn_11_lastname) {
		this.mn_11_lastname = mn_11_lastname;
	}

	public String getmn_12_lastname() {
		return mn_12_lastname;
	}

	public void setmn_12_lastname(String mn_12_lastname) {
		this.mn_12_lastname = mn_12_lastname;
	}

	public int getmn_10_phonenumber() {
		return mn_10_phonenumber;
	}

	public void setmn_10_phonenumber(int mn_10_phonenumber) {
		this.mn_10_phonenumber = mn_10_phonenumber;
	}

	public int getmn_11_phonenumber() {
		return mn_11_phonenumber;
	}

	public void setmn_11_phonenumber(int mn_11_phonenumber) {
		this.mn_11_phonenumber = mn_11_phonenumber;
	}

	public int getmn_12_phonenumber() {
		return mn_12_phonenumber;
	}

	public void setmn_12_phonenumber(int mn_12_phonenumber) {
		this.mn_12_phonenumber = mn_12_phonenumber;
	}

	public boolean isMn_10_isbusy() {

		return mn_10_isbusy;
	}

	public void setmn_10_isbusy(boolean mn_10_isbusy) { // Changed method name
		System.out.println("running setmn_10_isbusy");
		this.mn_10_isbusy = mn_10_isbusy;
	}

	public boolean isMn_11_isbusy() {
		return mn_11_isbusy;
	}

	public void setmn_11_isbusy(boolean mn_11_isbusy) {// Changed method name
		this.mn_11_isbusy = mn_11_isbusy;
	}

	public boolean isMn_12_isbusy() {
		return mn_12_isbusy;
	}

	public void setmn_12_isbusy(boolean mn_12_isbusy) {// Changed method name
		this.mn_12_isbusy = mn_12_isbusy;
	}

	public String getMn_10_username() {
		return mn_10_username;
	}

	public void setmn_10_username(String mn_10_username) {
		this.mn_10_username = mn_10_username;
	}

	public String getMn_11_username() {
		return mn_11_username;
	}

	public void setmn_11_username(String mn_11_username) {
		this.mn_11_username = mn_11_username;
	}

	public String getMn_12_username() {
		return mn_12_username;
	}

	public void setmn_12_username(String mn_12_username) {
		this.mn_12_username = mn_12_username;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Doctor(String username, String authority, int enabled, String firstName, String lastName, int phoneNumber,
			String email, String field, String password, boolean mn_10, boolean mn_11, boolean mn_12, String mn_10_text,
			String mn_11_text, String mn_12_text) {
		super();
		this.username = username;
		this.authority = authority;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.field = field;
		this.password = password;

		this.mn_10 = mn_10;
		this.mn_11 = mn_11;
		this.mn_12 = mn_12;
		this.mn_10_text = mn_10_text;
		this.mn_11_text = mn_11_text;
		this.mn_12_text = mn_12_text;
	}

	/*
	 * Replaced getters and setters for schedule.If its not working without it
	 * try to make new table special for schedule
	 */

	public Doctor() {
		super();
	}

	public boolean isMn_10() {
		return mn_10;
	}

	public void setMn_10(boolean mn_10) {
		this.mn_10 = mn_10;
	}

	public boolean isMn_11() {
		return mn_11;
	}

	public void setMn_11(boolean mn_11) {
		this.mn_11 = mn_11;
	}

	public boolean isMn_12() {
		return mn_12;
	}

	public void setMn_12(boolean mn_12) {
		this.mn_12 = mn_12;
	}

	public String getMn_10_text() {
		return mn_10_text;
	}

	public void setmn_10_text(String mn_10_text) {
		System.out.println("running setmn_10_text");
		this.mn_10_text = mn_10_text;
	}

	public String getMn_11_text() {
		return mn_11_text;
	}

	public void setmn_11_text(String mn_11_text) {
		this.mn_11_text = mn_11_text;
	}

	public String getMn_12_text() {
		return mn_12_text;
	}

	public void setmn_12_text(String mn_12_text) {
		this.mn_12_text = mn_12_text;
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