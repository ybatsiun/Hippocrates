package dao;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
@Component("doctorDao")
public class DoctorDao extends UserDao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void createDoctor(Doctor doctor) {
		session().save(doctor);
		System.out.println("Doctor registered");
	}
	
	public void createSchedule(Doctor doctor,boolean mn_10,boolean mn_11,boolean mn_12){
		doctor.setMn_10(mn_10);
		doctor.setMn_10(mn_11);
		doctor.setMn_10(mn_12);
		
		
		session().update(doctor);
		//think about transaktions at this place. see http://www.journaldev.com/3481/hibernate-session-merge-vs-update-save-saveorupdate-persist-example
	}

}
