package dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private PatientDao patientDao;
	/*
	 * @Autowired private Calendar calendar;
	 */

	private static final long serialVersionUID = 1L;

	public void createDoctor(Doctor doctor) {

		session().save(doctor);

		

		System.out.println("Creating calendar table...");

		for (int i = 1; i < 30; i++) {
			LocalDateTime localDateTime = LocalDateTime.now().plusDays(i);

			if (localDateTime.getDayOfWeek().toString() == "SATURDAY"
					|| localDateTime.getDayOfWeek().toString() == "SUNDAY") {
				continue;
			}

			for (int a = 8; a < 18; a++) {

				for (int b = 0; b < 60; b += 15) {

					localDateTime = LocalDateTime.now().plusDays(i).withHour(a).withMinute(b).withSecond(0);
					Calendar calendar = new Calendar(doctor);
					calendar.setDateTime(Timestamp.valueOf(localDateTime));

					calendar.doctor.setUsername(doctor.getUsername());

					calendar.setDay(localDateTime.getDayOfWeek().toString().toString());

					// System.out.println("Checking timetable hits...");
					if (calendar.getDay() == "MONDAY") {
						for (LocalTime time : doctor.getMonday()) {
							if (calendar.getDateTime().getHours() == time.getHour()
									&& calendar.getDateTime().getMinutes() == time.getMinute()) {
								calendar.setScheduled(true);
								/*
								 * System.out.println("For date "+
								 * calendar.getDateTime() );
								 * System.out.println("Hours check: " +
								 * calendar.getDateTime().getHours()+ " and "+
								 * time.getHour());
								 * System.out.println("Minutes check: " +
								 * calendar.getDateTime().getMinutes()+ " and "+
								 * time.getMinute());
								 */
							}
						}
					}
					session().save(calendar);
				}
			}
		}

		System.out.println("Doctor registered");
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> showSchedule(String username) {
	//	System.out.println("showSchedule in DAO");

		// Finding the closest Monday

		LocalDate today = LocalDate.now();
	//	System.out.println("Today is: " + today);
		
		do {
			int i = 1;
			today = today.plusDays(i);
		} while (today.getDayOfWeek().toString() != "MONDAY");
	//	System.out.println("The closest monday is:" + today);

		Date date = java.sql.Date.valueOf(today);
		//Taking all scheduled time sets from the closest week
		
		
		
		Query crit = session().createSQLQuery(
				"select dateTime,day,doctors.username from doctors  "
				+ "left outer join calendar on doctors.username = calendar.username "
				+ "where isScheduled=true and doctors.username='" +username
				+ "' and DATE_FORMAT(`dateTime`, '%d')="+date.getDate()
 );
				
				
		
	
		
		return  crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> showDoctors() {
		Criteria crit = session().createCriteria(Doctor.class);
		crit.add(Restrictions.eq("enabled", 1));
		/*
		 * crit.setProjection(Projections.projectionList().
		 * add(Projections.property("username"), "username")); it is working
		 */
		return crit.list();
	}

	public void bookAnAppointment(String doctorUsername, String patientUsername, String dayAndTime, String complain) {
		System.out.println("bookAnApp in DAO");
		Doctor doctor = (Doctor) session().get(Doctor.class, doctorUsername);

		String firstName = patientDao.getPatientByUsername(patientUsername).get(0).firstName;
		String lastName = patientDao.getPatientByUsername(patientUsername).get(0).lastName;
		int phoneNumber = patientDao.getPatientByUsername(patientUsername).get(0).phoneNumber;

		System.out.println("Current patient: " + firstName + " " + lastName + " " + phoneNumber);

		java.lang.reflect.Method method = null;

		try {
			method = doctor.getClass().getMethod("set" + dayAndTime + "_isbusy", boolean.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		try {
			method.invoke(doctor, true);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		;

		try {
			method = doctor.getClass().getMethod("set" + dayAndTime + "_text", String.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		try {
			method.invoke(doctor, complain);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		;

		try {
			method = doctor.getClass().getMethod("set" + dayAndTime + "_username", String.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		try {
			method.invoke(doctor, patientUsername);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		try {
			method = doctor.getClass().getMethod("set" + dayAndTime + "_phonenumber", int.class);
		} catch (SecurityException e) {
			System.out.println("security exception");
		} catch (NoSuchMethodException e) {
			System.out.println("No such method for phone number");
		}
		try {
			method.invoke(doctor, phoneNumber);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		try {
			method = doctor.getClass().getMethod("set" + dayAndTime + "_firstname", String.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		try {
			method.invoke(doctor, firstName);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		try {
			method = doctor.getClass().getMethod("set" + dayAndTime + "_lastname", String.class);
		} catch (SecurityException e) {
		} catch (NoSuchMethodException e) {
		}
		try {
			method.invoke(doctor, lastName);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}

		System.out.println("bookAnAppointment: " + doctorUsername + " and " + patientUsername + " and " + dayAndTime
				+ " and " + complain);
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

	

	public Doctor getDoctorByUsername(String username) {
		return (Doctor) session().get(Doctor.class, username);
	}
}