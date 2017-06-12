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

//TODO do showschedule for the next week method and rename showScheduledTimeForClosestWeek(String) method to show schedule 
// for the next week after next week
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
		System.out.println("In Doctor DAO creating a doctor with monday " + doctor.getMonday().toString());

		session().save(doctor);

		System.out.println("In Doctor Dao after saving using getByUsername"
				+ this.getDoctorByUsername(doctor.getUsername()).toString());

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

					if (calendar.getDay() == "TUESDAY") {
						for (LocalTime time : doctor.getTuesday()) {
							if (calendar.getDateTime().getHours() == time.getHour()
									&& calendar.getDateTime().getMinutes() == time.getMinute()) {
								calendar.setScheduled(true);

							}
						}
					}

					if (calendar.getDay() == "WEDNESDAY") {
						for (LocalTime time : doctor.getWednesday()) {
							if (calendar.getDateTime().getHours() == time.getHour()
									&& calendar.getDateTime().getMinutes() == time.getMinute()) {
								calendar.setScheduled(true);

							}
						}
					}

					if (calendar.getDay() == "THURSDAY") {
						for (LocalTime time : doctor.getThursday()) {
							if (calendar.getDateTime().getHours() == time.getHour()
									&& calendar.getDateTime().getMinutes() == time.getMinute()) {
								calendar.setScheduled(true);

							}
						}
					}

					if (calendar.getDay() == "FRIDAY") {
						for (LocalTime time : doctor.getFriday()) {
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
		System.out.println("In Doctor Dao after full registration using getByUsername"
				+ this.getDoctorByUsername(doctor.getUsername()).toString());
	}

	@SuppressWarnings("unchecked")
	public List<Timestamp> showScheduleTimeForNextNextWeek(String username) {

		// Finding the closest Monday

		LocalDateTime closestSunday = LocalDateTime.now().plusWeeks(1);
		if (closestSunday.getDayOfWeek().toString() != "SUNDAY") {
			do {
				int i = 1;
				closestSunday = closestSunday.plusDays(i);
			} while (closestSunday.getDayOfWeek().toString() != "SUNDAY");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedClosestSunday = closestSunday.format(formatter);

		LocalDateTime closestSaturdayToClosestSunday = closestSunday.plusDays(7);
		String formattedclosestSaturdayToClosestSunday = closestSaturdayToClosestSunday.format(formatter);

		// Taking all scheduled time sets from the closest week

		Query crit = session().createSQLQuery("select dateTime AS 'calendar.dateTime' from doctors  "
				+ "left outer join calendar on doctors.username = calendar.username "
				+ "where scheduled=true and doctors.username='" + username
				+ "' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') " + " between '" + formattedClosestSunday
				+ "' and '" + formattedclosestSaturdayToClosestSunday + "'");
		System.out.println("showScheduledTimeForClosestWeek in DoctorDao returns" + crit.list().toString());
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Calendar> showScheduleForCurrent_Next_and_NextNext_Weeks(String username) {

		LocalDate now = LocalDate.now();
		java.sql.Date nowDate = java.sql.Date.valueOf(now);

		java.sql.Date twoWeeksAfterNowDate = java.sql.Date.valueOf(now.plusWeeks(3));

		Criteria crit = session().createCriteria(Calendar.class)
				.setProjection(Projections.projectionList().add(Projections.property("dateTime"), "dateTime")
						.add(Projections.property("scheduled"), "scheduled").add(Projections.property("day"), "day")
						.add(Projections.property("busy"), "busy")
						.add(Projections.property("patientFirstName"), "patientFirstName")
						.add(Projections.property("patientLastName"), "patientLastName"))
				.setResultTransformer(Transformers.aliasToBean(Calendar.class))

				.add(Restrictions.eq("doctor.username", username)).add(Restrictions.eq("scheduled", true))
				.add(Restrictions.ge("dateTime", nowDate)).add(Restrictions.le("dateTime", twoWeeksAfterNowDate));
		System.out.println("In show schedule in DoctorDao" + crit.list().toString());
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


	public void editSchedule(ArrayList<LocalTime> monday, ArrayList<LocalTime> tuesday, ArrayList<LocalTime> wednesday,
			ArrayList<LocalTime> thursday, ArrayList<LocalTime> friday, String username) {


		LocalDateTime theMondayAfterClosestMonday = LocalDateTime.now().plusWeeks(1);
		if (theMondayAfterClosestMonday.getDayOfWeek().toString() != "SUNDAY") {
			do {
				int i = 1;
				theMondayAfterClosestMonday = theMondayAfterClosestMonday.plusDays(i);
			} while (theMondayAfterClosestMonday.getDayOfWeek().toString() != "SUNDAY");
		}

		System.out.println("date of the next sunday after next sunday  is " + theMondayAfterClosestMonday);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedString = theMondayAfterClosestMonday.format(formatter);

		// Deleting all scheduled appointments which are ahead of monday after
		// the closest
		// monday

		Query crit = session()
				.createSQLQuery(" update calendar " + "left outer join doctors on doctors.username=calendar.username "
						+ "set scheduled=false, busy=false where calendar.username= '" + username
						+ "' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString + "'");

		crit.executeUpdate();

		// Applying edited schedule

		for (LocalTime time : monday) {

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'MONDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : tuesday) {

			// time = java.sql.Time.valueOf(time);

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'TUESDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : wednesday) {

			// time = java.sql.Time.valueOf(time);

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'WEDNESDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : thursday) {

			// time = java.sql.Time.valueOf(time);

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'THURSDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : friday) {

			// time = java.sql.Time.valueOf(time);

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'FRIDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		System.out.println("Finished 'editSchedule' method");
	}

	public void bookAnAppointmentFor(String doctorUsername, long dateTime, String complain, String patientUsername) {

		// Getting details of logged in patient
		Criteria queryForPatientLastName = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList().add(Projections.property("lastName"), "lastName"))
				.add(Restrictions.eq("username", patientUsername));

		Criteria queryForPatientFirstName = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList().add(Projections.property("firstName"), "firstName"))
				.add(Restrictions.eq("username", patientUsername));

		Criteria queryForPatientEmail = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList().add(Projections.property("email"), "email"))
				.add(Restrictions.eq("username", patientUsername));

		Criteria queryForPatientPhone = session().createCriteria(Patient.class)
				.setProjection(Projections.projectionList().add(Projections.property("phoneNumber"), "phoneNumber"))
				.add(Restrictions.eq("username", patientUsername));

		String patientLastName = queryForPatientLastName.list().get(0).toString();
		String patientFirstName = queryForPatientFirstName.list().get(0).toString();
		String patientEmail = queryForPatientEmail.list().get(0).toString();
		String patientPhone = queryForPatientPhone.list().get(0).toString();

		LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime), ZoneId.systemDefault());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedString = date.format(formatter);

		System.out.println("Date and time for an appointment is " + date.toString());
		Query bookAnAppointment = session().createSQLQuery(" update calendar "
				+ "left outer join doctors on doctors.username=calendar.username "
				+ "set busy=true , patientFirstName= '" + patientFirstName + "' , patientLastName= '" + patientLastName
				+ "' , patientUserName= '" + patientUsername + "' , patientPhone= '" + patientPhone
				+ "' , patientEmail= '" + patientEmail + "' , complain= '" + complain + "' where calendar.username= '"
				+ doctorUsername + "' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') = '" + formattedString + "'");

		bookAnAppointment.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Calendar> showDoctorsScheduleForNextWeek(String username) {
		// Getting JSON of Calendar entity for current Doctor for the next month

		LocalDate today = LocalDate.now();
		java.sql.Date now = java.sql.Date.valueOf(today);

		java.sql.Date plusAMonth = java.sql.Date.valueOf(today.plusWeeks(1));

		Criteria crit = session().createCriteria(Calendar.class)
				.setProjection(Projections.projectionList().add(Projections.property("dateTime"), "dateTime")
						.add(Projections.property("scheduled"), "scheduled").add(Projections.property("day"), "day")
						.add(Projections.property("busy"), "busy")
						.add(Projections.property("patientFirstName"), "patientFirstName")
						.add(Projections.property("patientLastName"), "patientLastName"))
				.setResultTransformer(Transformers.aliasToBean(Calendar.class))

				.add(Restrictions.eq("doctor.username", username)).add(Restrictions.eq("scheduled", true))
				.add(Restrictions.ge("dateTime", now)).add(Restrictions.le("dateTime", plusAMonth));

		

		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Doctor> getDoctorDetails(String doctorUserName) {
		Criteria crit = session().createCriteria(Doctor.class).setProjection(Projections.projectionList()
				.add(Projections.property("email"), "email")
				.add(Projections.property("field"), "field")
				.add(Projections.property("firstName"), "firstName")
				.add(Projections.property("lastName"), "lastName")
				.add(Projections.property("phoneNumber"), "phoneNumber")
				.add(Projections.property("password"), "password"))
				
		
		.add(Restrictions.eq("username", doctorUserName));
		
		return crit.list();
	}

}
