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



@Entity
@Table(name = "users")
public class User implements Serializable {
   
	
	@GeneratedValue
	@Column(name="id")
	public int id ;
	
	
	@Column(name="authority")
	public  String authority;

	@Column(name="password")
	public String password;

	@Column(name="enabled")
	public int enabled ;

	@Id
	@Column(name="username")
		public String username;

	
	
	
	
	
    public int getId() {
		return id;
	}


	public int getEnabled() {
		return enabled;
	}
	
	
	
	/*@OneToOne(cascade = CascadeType.ALL)*/
	public String getUsername() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}

	
	public String getAuthority() {
		return authority;
	}

	/*public void setId(int id) {
		this.id = id;
	}*/

	public void setUsername(String username) {
		this.username = username;
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

	
	public User(String username, int enabled, String password, String authority) {
		super();
		this.username = username;
		this.enabled = enabled;
		this.password = password;
		this.authority = authority;
	}

	public User() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}}
