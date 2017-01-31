package dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import service.DoctorsService;


@Repository
@Transactional
@Component("doctorDao")
public class DoctorDao extends UserDao implements Serializable {
	/**
	 * 
	 */
	
	@Autowired
	private PatientDao patientDao;
	
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
	
	public void bookAnAppointment(String doctorUsername,String patientUsername,String dayAndTime,String complain){
System.out.println("bookAnApp in DAO");
		Doctor doctor=(Doctor) session().get(Doctor.class, doctorUsername);
		
		String firstName=patientDao.getPatientByUsername(patientUsername).get(0).firstName;
		String lastName=patientDao.getPatientByUsername(patientUsername).get(0).lastName;
		int phoneNumber=patientDao.getPatientByUsername(patientUsername).get(0).phoneNumber;
	
		System.out.println("Current patient: "+firstName+" " + lastName+ " " + phoneNumber );
		
		
		
		java.lang.reflect.Method method=null;
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"_isbusy", boolean.class);
			} catch (SecurityException e) {  }
			  catch (NoSuchMethodException e) {  }
		try {
			  method.invoke(doctor, true);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  };
		
		
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"_text", String.class);
			} catch (SecurityException e) {  }
			  catch (NoSuchMethodException e) {  }
		try {
			  method.invoke(doctor, complain);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  };
		
		
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"_username", String.class);
			} catch (SecurityException e) {  }
			  catch (NoSuchMethodException e) {  }
		try {
			  method.invoke(doctor, patientUsername);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  }
		
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"_phonenumber", int.class);
			} catch (SecurityException e) { System.out.println("security exception"); }
			  catch (NoSuchMethodException e) { System.out.println("No such method for phone number"); }
		try {
			  method.invoke(doctor, phoneNumber);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  }
		
		
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"_firstname", String.class);
			} catch (SecurityException e) {  }
			  catch (NoSuchMethodException e) {  }
		try {
			  method.invoke(doctor, firstName);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  }
		
		try {
			  method = doctor.getClass().getMethod("set"+dayAndTime+"_lastname", String.class);
			} catch (SecurityException e) {  }
			  catch (NoSuchMethodException e) {  }
		try {
			  method.invoke(doctor, lastName);
			} catch (IllegalArgumentException e) {  }
			  catch (IllegalAccessException e) {  }
			  catch (InvocationTargetException e) {  }
		
		
		
		
		System.out.println("bookAnAppointment: "+doctorUsername+" and "+ patientUsername+" and "+ dayAndTime+" and "+complain);
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> showDoctorsForAdmin() {
		Criteria crit = session().createCriteria(Doctor.class);
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> showSchedules() {
		Criteria crit = session().createCriteria(Doctor.class);
		
		return crit.list();
	}
	}
	
	
	

