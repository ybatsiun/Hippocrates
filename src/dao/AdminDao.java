package dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("adminDao")
public class AdminDao extends UserDao implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	
	public void createDoctor(Admin admin) {
		session().save(admin);
		System.out.println("Doctor registered");
	}

}
