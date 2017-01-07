package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	public List<Doctor> showSchedule(String username) {
		System.out.println("Starting in DAO...");
		Criteria crit = session().createCriteria(Doctor.class);
		System.out.println("Criteria created");
		crit.add(Restrictions.eq("username", username));
		System.out.println("Got username" + username);
		for (int i = 10; i < 13; i++) {
			crit.add(Restrictions.eq("Mn_" + i, true));
			System.out.println("Restriction added for Mn_" + i);
		}

		for (int i = 10; i < 13; i++) {
			crit.add(Restrictions.isNotNull("Mn_" + i + "_text"));
			System.out.println("Restriction added for Mn_" + i + "_text");
		}
		return crit.list();
	}

}
