package dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/*@Entity
@Table(name = "users")*/
public class User implements Serializable {
   
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  String authority;

	public String password;

	public int enabled ;



	
	
	
	


	public int getEnabled() {
		return enabled;
	}
	
	
	
	
	
	
	public String getPassword() {
		return password;
	}

	
	public String getAuthority() {
		return authority;
	}

	

	

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public int isEnabled() {
		return enabled;
	}

	
	public User( int enabled, String password, String authority) {
		super();
		
		this.enabled = enabled;
		this.password = password;
		this.authority = authority;
	}

	public User() {
		super();
	}

	

	}
