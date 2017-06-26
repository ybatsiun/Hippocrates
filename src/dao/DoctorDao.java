package dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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

/*This class contains methods to work with data of doctor object. Each of this methods is duplicated in the service layer (DoctorService)*/
@Repository
@Transactional
@Component("doctorDao")
public class DoctorDao extends UserDao implements Serializable {

	@Autowired
	private PatientDao patientDao;

	private static final long serialVersionUID = 1L;

	/*
	 * First creates doctor object in DB. Fields of doctor object like
	 * monday,tuesday etc. contain a chosen by a user 1 hour intervals - the
	 * schedule. This intervals are transfered to a 15 min intervals. Afterwards
	 * the method creates time spots (calendar objects) for this doctor object .
	 * Each time spot is a 15 min interval from 08:00 to 18:00 - time interval
	 * during which a doctor can chose a schedule. When each spot is being
	 * created, method checks what day of the week it is and ,depending on the
	 * day it checks whether the corresponding field in doctor object (monday,
	 * tuesday etc.) contains this time spot. If yes this time spot is marked
	 * with scheduled=true.
	 */
	public void createDoctor(Doctor doctor) {
		System.out.println("In Doctor DAO creating a doctor with monday " + doctor.getMonday().toString());

		session().save(doctor);
		doctor = this.transferHoursListToIntervalsList(doctor, doctor.getMonday(), doctor.getTuesday(),
				doctor.getWednesday(), doctor.getThursday(), doctor.getFriday());
		// Making 15 min interval lists from 1 hour list and passing these list
		// to current doctor object

		System.out.println("Doctor list for monday before transfer " + doctor.getMonday().toString());

		// _______________________________________________________________________________
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


	/*
	 * First the method finds the closest sunday to the moment and adds one week.
	 *  The method returns a list of timestamp objects
	 * which is a for scheduled time the next week after next week after current
	 * week. This method shows the whole week (from monday to friday)
	 */
	@SuppressWarnings("unchecked")
	public List<Timestamp> showScheduleTimeForNextNextWeek(String username) {
		System.out.println("____________________________________________");

		System.out.println("I am showScheduleTimeForNextNextWeek");

		LocalDateTime sundayAfterclosestSunday = LocalDateTime.now().plusWeeks(1);
		if (sundayAfterclosestSunday.getDayOfWeek().toString() != "SUNDAY") {
			do {
				int i = 1;
				sundayAfterclosestSunday = sundayAfterclosestSunday.plusDays(i);
			} while (sundayAfterclosestSunday.getDayOfWeek().toString() != "SUNDAY");
		}
		System.out.println("Start : " +  sundayAfterclosestSunday);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedClosestSunday = sundayAfterclosestSunday.format(formatter);

		LocalDateTime closestSaturdayToClosestSunday = sundayAfterclosestSunday.plusDays(7);
		String formattedclosestSaturdayToClosestSunday = closestSaturdayToClosestSunday.format(formatter);
		System.out.println("Finish : " +  closestSaturdayToClosestSunday);
		System.out.println("____________________________________________");

		// Taking all scheduled time sets from the closest week

		Query crit = session().createSQLQuery("select dateTime AS 'calendar.dateTime' from doctors  "
				+ "left outer join calendar on doctors.username = calendar.username "
				+ "where scheduled=true and doctors.username='" + username
				+ "' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') " + " between '" + formattedClosestSunday
				+ "' and '" + formattedclosestSaturdayToClosestSunday + "'");
		System.out.println("showScheduledTimeForClosestWeek in DoctorDao returns" + crit.list().toString());
		return crit.list();
	}

	/*
	 * First the method finds date for now-moment. The method returns a list of timestamp objects
	 * which is a for scheduled time the next week after next week after current
	 * week. This method does not show the full week. If today if wednesday by current week it means the week till
	 * the next wednesday and next and next-next weeks the same.
	 */
	@SuppressWarnings("unchecked")
	public List<Calendar> showScheduleForCurrent_Next_and_NextNext_Weeks(String username) {
		System.out.println("____________________________________________");

		System.out.println("showScheduleForCurrent_Next_and_NextNext_Weeks");

		LocalDate now = LocalDate.now();
		java.sql.Date nowDate = java.sql.Date.valueOf(now);
		System.out.println("Start: " + now);


		java.sql.Date threeWeeksAfterNowDate = java.sql.Date.valueOf(now.plusWeeks(3));
		System.out.println("Finish: " + threeWeeksAfterNowDate);
		System.out.println("____________________________________________");

		Criteria crit = session().createCriteria(Calendar.class)
				.setProjection(Projections.projectionList().add(Projections.property("dateTime"), "dateTime")
						.add(Projections.property("scheduled"), "scheduled").add(Projections.property("day"), "day")
						.add(Projections.property("busy"), "busy").add(Projections.property("complain"), "complain")
						.add(Projections.property("patientFirstName"), "patientFirstName")
						.add(Projections.property("patientLastName"), "patientLastName"))
				.setResultTransformer(Transformers.aliasToBean(Calendar.class))

				.add(Restrictions.eq("doctor.username", username)).add(Restrictions.eq("scheduled", true))
				.add(Restrictions.ge("dateTime", nowDate)).add(Restrictions.le("dateTime", threeWeeksAfterNowDate));
		System.out.println("In show schedule in DoctorDao" + crit.list().toString());
		return crit.list();
	}

	/*
	 * First the method finds now-date and adds two weeks to it. The method returns a list of timestamp objects
	 * which is a for scheduled time the next week after next week after current
	 * week.This method does not show the full week. If today if wednesday by current week it means the week till
	 * the next wednesday and next and next-next weeks the same.
	 */
	@SuppressWarnings("unchecked")
	public List<Calendar> showScheduleForCurrent_and_NextWeek(String username) {
		// Getting JSON of Calendar entity for current Doctor for the next month
		System.out.println("____________________________________________");
		System.out.println("showScheduleForCurrent_and_NextWeek");

		LocalDate today = LocalDate.now();
		java.sql.Date now = java.sql.Date.valueOf(today);
		System.out.println("Start: " + now);

		java.sql.Date plusAMonth = java.sql.Date.valueOf(today.plusWeeks(2));
		System.out.println("Finish: " + plusAMonth);

		System.out.println("____________________________________________");

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

	/*
	 * The method returns a list of all doctors which have enabled=1 in the DB
	 */
	@SuppressWarnings("unchecked")
	public List<Doctor> showDoctors() {
		Criteria crit = session().createCriteria(Doctor.class);

		crit.add(Restrictions.eq("enabled", 1)).setProjection(Projections.projectionList()
				.add(Projections.property("username"), "username").add(Projections.property("lastName"), "lastName")
				.add(Projections.property("firstName"), "firstName").add(Projections.property("field"), "field"))
				.setResultTransformer(Transformers.aliasToBean(Doctor.class));

		return crit.list();
	}

	

	/* The method returns a doctor with username passed to a method*/
	public Doctor getDoctorByUsername(String username) {
		return (Doctor) session().get(Doctor.class, username);
	}
	
	
/* The method is designed to rewrite the part of the schedule of a doctor. Method's parameters are username of a doctor and 5 ArrayLists which define 
 * new (edited) schedule. First the method finds a day after a new schedule must be implemented (monday of the next-next week) . Then a schedule after this day is deleted be passing false to 
 * a parameters busy and scheduled. Afterwards, for every time spot in each List which is responsible for a particular day of the week and sql update query finds a 
 * a calendar object entity with a proper day of the week and the same time spot and passes true to scheduled parameter. These updates are implemented only for timespots
 * starting from the monday of next-next week */
	public void editSchedule(ArrayList<LocalTime> monday, ArrayList<LocalTime> tuesday, ArrayList<LocalTime> wednesday,
			ArrayList<LocalTime> thursday, ArrayList<LocalTime> friday, String username) {
		// Transfering from hour to 15 min lists of time
		Doctor doctor = new Doctor();
		doctor = this.transferHoursListToIntervalsList(doctor, monday, tuesday, wednesday, thursday, friday);

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

		for (LocalTime time : doctor.getMonday()) {

			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'MONDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : doctor.getTuesday()) {


			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'TUESDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : doctor.getWednesday()) {


			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'WEDNESDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : doctor.getThursday()) {


			Query editSchedule = session().createSQLQuery(" update calendar "
					+ "left outer join doctors on doctors.username=calendar.username "
					+ "set scheduled=true where calendar.username= '" + username
					+ "' and day= 'THURSDAY' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') >= '" + formattedString

					+ "' and DATE_FORMAT(`dateTime`, '%H') = " + time.getHour()
					+ " and DATE_FORMAT(`dateTime`, '%i') = " + time.getMinute());

			editSchedule.executeUpdate();
		}

		for (LocalTime time : doctor.getFriday()) {


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
/*The method first gets the details of a patient who is logged in. After it finds a calendar object which has a defined in parameters doctors username, dateTime
 * and passes patients info, busy=true to this calendar object.*/
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
		
		Query bookAnAppointment = session().createSQLQuery(" update calendar "
				+ "left outer join doctors on doctors.username=calendar.username "
				+ "set busy=true , patientFirstName= '" + patientFirstName + "' , patientLastName= '" + patientLastName
				+ "' , patientUserName= '" + patientUsername + "' , patientPhone= '" + patientPhone
				+ "' , patientEmail= '" + patientEmail + "' , complain= '" + complain + "' where calendar.username= '"
				+ doctorUsername + "' and DATE_FORMAT(`dateTime`, '%Y-%m-%d %H:%i:%s') = '" + formattedString + "'");

		
		bookAnAppointment.executeUpdate();
	}
/*The method returns a doctor info with a username defined in the parameter to a method*/
	@SuppressWarnings("unchecked")
	public List<Doctor> getDoctorDetails(String doctorUserName) {
		Criteria crit = session().createCriteria(Doctor.class)
				.setProjection(Projections.projectionList().add(Projections.property("email"), "email")
						.add(Projections.property("field"), "field").add(Projections.property("firstName"), "firstName")
						.add(Projections.property("lastName"), "lastName")
						.add(Projections.property("phoneNumber"), "phoneNumber")
						.add(Projections.property("password"), "password"))

				.add(Restrictions.eq("username", doctorUserName));

		return crit.list();
	}
/*The method takes 5 lists of a doctor object and LocalTime objects . Each contains an hour interval ( general formula is XX:00, where XX is an hour between 08 and 18).
 * Then a method transfers these 1 hour intervals to a 15 min intervals and passes the to a doctor object. After all a doctor object with the 15 min intervals as a 
 * monday,tuesday etc. fields is returned. */
	public Doctor transferHoursListToIntervalsList(Doctor doctor, List<LocalTime> monday, List<LocalTime> tuesday,
			List<LocalTime> wednesday, List<LocalTime> thursday, List<LocalTime> friday) {

		ArrayList<LocalTime> intervalsMonday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> intervalsTuesday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> intervalsWednesday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> intervalsThursday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> intervalsFriday = new ArrayList<LocalTime>();

		for (LocalTime time : monday) {

			for (int i = 0; i < 60; i += 15) {
				intervalsMonday.add(LocalTime.of(time.getHour(), i));
			}
		}
		doctor.setMonday(intervalsMonday);
		System.out.println("Monday after transfer" + doctor.getMonday().toString());
		System.out.println("Monday after clearing intervals list" + doctor.getMonday().toString());

		for (LocalTime time : tuesday) {

			for (int i = 0; i < 60; i += 15) {
				intervalsTuesday.add(LocalTime.of(time.getHour(), i));
			}
		}
		doctor.setTuesday(intervalsTuesday);

		for (LocalTime time : wednesday) {

			for (int i = 0; i < 60; i += 15) {
				intervalsWednesday.add(LocalTime.of(time.getHour(), i));
			}
		}
		doctor.setWednesday(intervalsWednesday);

		for (LocalTime time : thursday) {

			for (int i = 0; i < 60; i += 15) {
				intervalsThursday.add(LocalTime.of(time.getHour(), i));
			}
		}
		doctor.setThursday(intervalsThursday);

		for (LocalTime time : friday) {

			for (int i = 0; i < 60; i += 15) {
				intervalsFriday.add(LocalTime.of(time.getHour(), i));
			}
		}
		doctor.setFriday(intervalsFriday);

		System.out.println("Doctor to be saved : " + doctor.toString());

		return doctor;
	}

	
/*The same a create doctor method but created much less calendar object. Designed in testing purposes.*/
	public void createDoctorForTest(Doctor doctor) {
		System.out.println("In Doctor DAO creating a doctor with monday " + doctor.getMonday().toString());

		session().save(doctor);
		doctor = this.transferHoursListToIntervalsList(doctor, doctor.getMonday(), doctor.getTuesday(),
				doctor.getWednesday(), doctor.getThursday(), doctor.getFriday());
		// Making 15 min interval lists from 1 hour list and passing these list
		// to current doctor object

		System.out.println("Doctor list for monday before transfer " + doctor.getMonday().toString());

		// _______________________________________________________________________________
		System.out.println("Creating calendar table...");
		// variable i determines on how much days ahead you create schedule in
		// calendar table
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
}
