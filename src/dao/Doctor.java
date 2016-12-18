package dao;

public class Doctor extends User {

	
	
	private static final long serialVersionUID = 1L;
	public Doctor(String username, boolean enabled, String authority) {
		super(username, enabled, authority);
	}
	private String firstName;
	private String lastName;
	
	private int phoneNumber;
	private String email;
	private String field;
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
	public Doctor(String username, boolean enabled, String authority, String firstName, String lastName,
			int phoneNumber, String email, String field) {
		super(username, enabled, authority);
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.field = field;
	}
}
