package dao;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
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

/*Doctor is a class that corresponds to an entity doctor (role) in the database. It has certain fields for 
a personal info and fields to define a schedule of a user. 

Blank fields like mondayBlank, tuesdayBlank etc. are created immediately when the object 
is created (see the constructor without parameters). These blank fields are then used to generated 
items in a web-page (/createDoctor,/editSchedule) when creating doctor or editing a schedule. 

Fields like monday, tuesday etc. after creating or editing schedule/doctor contain a chosen schedule.
By schedule it is meant intervals of an hour-size,during which a doctor works,which are then transfered to
a 15-min interval (DoctorDao.transferHoursListToIntervalsList method).

Doctor object has a filed called calendar. This is a list of Calendar objects. This implements a 
one (Doctor) to many (Calendar) database relationship. For more info about Calendar object please see
Calendar class file.
*/
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

	@Pattern(regexp = "0\\d{9}", message = "please enter a valid phone number starting from '0' and following by 9 digits")
	public String phoneNumber;

	@NotEmpty(message = "please enter your email")
	@Email(message = "email does not appear to be valid")
	public String email;

	@NotEmpty(message = "please enter  your field")
	public String field;

	@Size(min = 10, max = 20, message = "Password must between 10 and 20 characters")
	public String password;
	@ValidSchedule
	public ArrayList<LocalTime> monday;

	public ArrayList<LocalTime> mondayBlank;
	@ValidSchedule
	public ArrayList<LocalTime> tuesday;

	public ArrayList<LocalTime> tuesdayBlank;
	@ValidSchedule
	public ArrayList<LocalTime> wednesday;

	public ArrayList<LocalTime> wednesdayBlank;
	@ValidSchedule
	public ArrayList<LocalTime> thursday;

	public ArrayList<LocalTime> thursdayBlank;
	@ValidSchedule
	public ArrayList<LocalTime> friday;

	public ArrayList<LocalTime> fridayBlank;

	private static final long serialVersionUID = 1L;

	public List<Calendar> calendar;

	/*
	 * Replaced getters and setters for schedule.If its not working without it
	 * try to make new table special for schedule
	 */

	public Doctor() {

		ArrayList<LocalTime> constructor = new ArrayList<LocalTime>();
		for (int a = 8; a < 18; a++) {

			constructor.add(LocalTime.of(a, 0));

		}
		this.setMondayBlank(constructor);
		this.setTuesdayBlank(constructor);
		this.setWednesdayBlank(constructor);
		this.setThursdayBlank(constructor);
		this.setFridayBlank(constructor);
	}

	public Doctor(ArrayList<LocalTime> monday) {
		super();
		this.monday = monday;
	}

	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return "Doctor [username=" + username + ", enabled=" + enabled + ", authority=" + authority + ", firstName="
				+ firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", field=" + field + ", password=" + password + ", monday=" + monday + ", mondayBlank=" + mondayBlank
				+ ", tuesday=" + tuesday + ", tuesdayBlank=" + tuesdayBlank + ", wednesday=" + wednesday
				+ ", wednesdayBlank=" + wednesdayBlank + ", thursday=" + thursday + ", thursdayBlank=" + thursdayBlank
				+ ", friday=" + friday + ", fridayBlank=" + fridayBlank + "]";
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

	@Transient
	public ArrayList<LocalTime> getTuesday() {
		return tuesday;
	}

	public void setTuesday(ArrayList<LocalTime> tuesday) {
		this.tuesday = tuesday;
	}

	@Transient
	public ArrayList<LocalTime> getTuesdayBlank() {
		return tuesdayBlank;
	}

	public void setTuesdayBlank(ArrayList<LocalTime> tuesdayBlank) {
		this.tuesdayBlank = tuesdayBlank;
	}

	@Transient
	public ArrayList<LocalTime> getWednesday() {
		return wednesday;
	}

	public void setWednesday(ArrayList<LocalTime> wednesday) {
		this.wednesday = wednesday;
	}

	@Transient
	public ArrayList<LocalTime> getWednesdayBlank() {
		return wednesdayBlank;
	}

	public void setWednesdayBlank(ArrayList<LocalTime> wednesdayBlank) {
		this.wednesdayBlank = wednesdayBlank;
	}

	@Transient
	public ArrayList<LocalTime> getThursday() {
		return thursday;
	}

	public void setThursday(ArrayList<LocalTime> thursday) {
		this.thursday = thursday;
	}

	@Transient
	public ArrayList<LocalTime> getThursdayBlank() {
		return thursdayBlank;
	}

	public void setThursdayBlank(ArrayList<LocalTime> thursdayBlank) {
		this.thursdayBlank = thursdayBlank;
	}

	@Transient
	public ArrayList<LocalTime> getFriday() {
		return friday;
	}

	public void setFriday(ArrayList<LocalTime> friday) {
		this.friday = friday;
	}

	@Transient
	public ArrayList<LocalTime> getFridayBlank() {
		return fridayBlank;
	}

	public void setFridayBlank(ArrayList<LocalTime> fridayBlank) {
		this.fridayBlank = fridayBlank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + enabled;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}