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
	public boolean Mn_10;
	public boolean Mn_11;
	public boolean Mn_12;
	public String Mn_10_text;
	public String Mn_11_text;
	public String Mn_12_text;
	
	
	public boolean isMn_10() {
		return Mn_10;
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
		Mn_10 = mn_10;
		Mn_11 = mn_11;
		Mn_12 = mn_12;
		Mn_10_text = mn_10_text;
		Mn_11_text = mn_11_text;
		Mn_12_text = mn_12_text;
	}


	public void setMn_10(boolean mn_10) {
		Mn_10 = mn_10;
	}


	public boolean isMn_11() {
		return Mn_11;
	}


	public void setMn_11(boolean mn_11) {
		Mn_11 = mn_11;
	}


	public boolean isMn_12() {
		return Mn_12;
	}


	public void setMn_12(boolean mn_12) {
		Mn_12 = mn_12;
	}


	public String getMn_10_text() {
		return Mn_10_text;
	}


	public void setMn_10_text(String mn_10_text) {
		Mn_10_text = mn_10_text;
	}


	public String getMn_11_text() {
		return Mn_11_text;
	}


	public void setMn_11_text(String mn_11_text) {
		Mn_11_text = mn_11_text;
	}


	public String getMn_12_text() {
		return Mn_12_text;
	}


	public void setMn_12_text(String mn_12_text) {
		Mn_12_text = mn_12_text;
	}


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
