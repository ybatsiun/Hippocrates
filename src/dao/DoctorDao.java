package dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
		// variable i determines on how much days ahead you create schedule in
		// calendar table
		for (int i = 1; i < 100; i++) {
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

					if (calendar.getDay() == "MONDAY") {
						for (LocalTime time : doctor.getMonday()) {
							if (calendar.getDateTime().getHours() == time.getHour()
									&& calendar.getDateTime().getMinutes() == time.getMinute()) {
								calendar.setScheduled(true);

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
	public List<Timestamp> showScheduledTimeForClosestWeek(String username) {
		// System.out.println("showSchedule in DAO");

		// Finding the closest Monday

		LocalDate today = LocalDate.now();

		do {
			int i = 1;
			today = today.plusDays(i);
		} while (today.getDayOfWeek().toString() != "MONDAY");

		Date date = java.sql.Date.valueOf(today);
		// Taking all scheduled time sets from the closest week

		Query crit = session().createSQLQuery("select dateTime AS 'calendar.dateTime' from doctors  "
				+ "left outer join calendar on doctors.username = calendar.username "
				+ "where scheduled=true and doctors.username='" + username + "' and DATE_FORMAT(`dateTime`, '%d')="
				+ date.getDate());

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> showDoctors() {
		Criteria crit = session().createCriteria(Doctor.class);

		crit.add(Restrictions.eq("enabled", 1)).setProjection(Projections.projectionList()
				.add(Projections.property("username"), "username").add(Projections.property("lastName"), "lastName")
				.add(Projections.property("firstName"), "firstName").add(Projections.property("field"), "field"))
				.setResultTransformer(Transformers.aliasToBean(Doctor.class));

		return crit.list();
	}

	public void bookAnAppointment(String doctorUsername, String patientUsername, String dayAndTime, String complain) {
		System.out.println("bookAnApp in DAO");
		Doctor doctor = (Doctor) session().get(Doctor.class, doctorUsername);

		String firstName = patientDao.getPatientByUsername(patientUsername).get(0).firstName;
		String lastName = patientDao.getPatientByUsername(patientUsername).get(0).lastName;
		String phoneNumber = patientDao.getPatientByUsername(patientUsername).get(0).phoneNumber;

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
		Criteria crit = session().createCriteria(Doctor.class)

		;

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

	public void editSchedule(ArrayList<LocalTime> monday, String username) {

		// Getting the closest monday

		LocalDateTime closestMonday = LocalDateTime.now();

		do {
			int i = 1;
			closestMonday = closestMonday.plusDays(i);
		} while (closestMonday.getDayOfWeek().toString() != "SUNDAY");
		System.out.println("date of closest monday  is " + closestMonday);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedString = closestMonday.format(formatter);

		System.out.println("date of closest monday  is " + formattedString);

		// Deleting all scheduled appointments which are ahead of the closest
		// monday
		System.out.println("Before SQL update statement...");

		Query crit = session().createSQLQuery(" update calendar "
				+ "left outer join doctors on doctors.username=calendar.username "
				+ "set scheduled=false, busy=false where calendar.username= '" + username
				+ "' and day= 'MONDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString + "'");

		crit.executeUpdate();

		System.out.println("After SQL update statement");

		// Applying edited schedule

		for (LocalTime time : monday) {

			// time = java.sql.Time.valueOf(time);

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'MONDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		System.out.println("Finished 'editSchedule' method");
	}

	@SuppressWarnings("unchecked")
	public List<Calendar> showDoctorsScheduleForNextMonth(String username) {
		// Getting JSON of Calendar entity for current Doctor for the next month

		LocalDate today = LocalDate.now();
		java.sql.Date now = java.sql.Date.valueOf(today);

		java.sql.Date plusAMonth = java.sql.Date.valueOf(today.plusMonths(1));

		Criteria crit = session().createCriteria(Calendar.class)
				.setProjection(Projections.projectionList().add(Projections.property("dateTime"), "dateTime")
						.add(Projections.property("scheduled"), "scheduled").add(Projections.property("day"), "day")
						.add(Projections.property("busy"), "busy")
						.add(Projections.property("patientFirstName"), "patientFirstName")
						.add(Projections.property("patientLastName"), "patientLastName"))
				.setResultTransformer(Transformers.aliasToBean(Calendar.class))

				.add(Restrictions.eq("doctor.username", username)).add(Restrictions.eq("scheduled", true))
				.add(Restrictions.ge("dateTime", now)).add(Restrictions.le("dateTime", plusAMonth));

		//String FILENAME = "K:\\filename.txt";

		/*try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {

			bw.write("Amount of enteties returned in showDoctorsScheduleForNextMonth is " + crit.list().size());

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		}*/

		return crit.list();
	}

	public void bookAnAppointmentFor(String doctorUsername, long dateTime, String complain, String patientUsername) {
		
		
		//Getting details of logged in patient
		Criteria queryForPatientLastName = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList()
				.add(Projections.property("lastName"), "lastName"))
				.add(Restrictions.eq("username", patientUsername));
		
		
		Criteria queryForPatientFirstName = session().createCriteria(Patient.class)
					.setProjection(Projections.projectionList()
					.add(Projections.property("firstName"), "firstName"))
					.add(Restrictions.eq("username", patientUsername));
			
	
		Criteria queryForPatientEmail = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList()
				.add(Projections.property("email"), "email"))
				.add(Restrictions.eq("username", patientUsername));
	
	
		Criteria queryForPatientPhone = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList()
				.add(Projections.property("phoneNumber"), "phoneNumber"))
				.add(Restrictions.eq("username", patientUsername));
	
		String patientLastName = queryForPatientLastName.list().get(0).toString();
		String patientFirstName = queryForPatientFirstName.list().get(0).toString();
		String patientEmail = queryForPatientEmail.list().get(0).toString();
		int patientPhone = (int) queryForPatientPhone.list().get(0);
	
		
		LocalDateTime date =
			    LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime), ZoneId.systemDefault());
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedString = date.format(formatter);
		
		System.out.println("Date and time for an appointment is " + date.toString());
		Query bookAnAppointment = session().createSQLQuery(" update calendar "
				+ "left outer join doctors on doctors.username=calendar.username "
				+ "set busy=true , patientFirstName= '"+patientFirstName+"' , patientLastName= '"+ patientLastName 
				+ "' , patientUserName= '"+patientUsername +"' , patientPhone= '" + patientPhone 
				+ "' , patientEmail= '"+patientEmail + "' , complain= '" + complain
				+ "' where calendar.username= '" + doctorUsername
				+ "' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') = '" + formattedString+"'");

		bookAnAppointment.executeUpdate();
	}

}
