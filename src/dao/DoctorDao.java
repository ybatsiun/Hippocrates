package dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
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
		crit.add(Restrictions.eq("username", username));
		
		
		
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List <Doctor> showDoctors(){
		Criteria crit = session().createCriteria(Doctor.class);
		crit.add(Restrictions.eq("enabled",1));
		/*crit.setProjection(Projections.projectionList().
		add(Projections.property("username"), "username")); it is working */
		return crit.list();
	}
	
	public void bookAnAppointment(String doctorUsername,String patientUsername,String dayAndTime/*,String complain*/){
		Doctor doctor=(Doctor) session().get(Doctor.class, doctorUsername);
		
		java.lang.reflect.Method method=null;
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"isbusy", boolean.class);
			} catch (SecurityException e) {  }
			  catch (NoSuchMethodException e) {  }
		
		try {
			  method.invoke(doctor, true);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  }
	}

}
