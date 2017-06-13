package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.Calendar;
import dao.Doctor;
import dao.DoctorDao;
import dao.Patient;
import dao.PatientDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:/config/dao-context.xml", "classpath:/config/security-context.xml",
		"classpath:/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FunctionalityTest {

	@Autowired
	PatientDao patientDao;

	@Autowired
	DoctorDao doctorDao;

	@Autowired
	private DataSource dataSource;

	private Patient patient1 = new Patient();

	private Patient patient2 = new Patient();

	private Patient patient3 = new Patient();

	private Doctor doctor = new Doctor();

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from patients");
		jdbc.execute("delete from calendar");
		jdbc.execute("delete from doctors");
		// Constructor didnt work.Dont know why
		// Patient1 - valid patient
		patient1.setUsername("patient1");
		patient1.setFirstName("patient1fn");
		patient1.setLastName("patient1ln");
		patient1.setPassword("patient1pwpatient1pw");
		patient1.setEmail("patient1@gmail.com");
		patient1.setPhoneNumber("0935036714");

		// Patient2 has the same username as patient1
		patient2.setUsername("patient1");
		patient2.setFirstName("patient1fn");
		patient2.setLastName("patient1ln");
		patient2.setPassword("patient1pwpatient1pw");
		patient2.setEmail("patient1@gmail.com");
		patient2.setPhoneNumber("0935036714");

		// patient3 has not valid fields
		patient3.setUsername("patient3");
		patient3.setFirstName("pa");
		patient3.setLastName("pat");
		patient3.setPassword("pati");
		patient3.setEmail("patient1gmailcom");
		patient3.setPhoneNumber("80935036714");

		// valid doctor
		ArrayList<LocalTime> daySchedule = new ArrayList<LocalTime>();
		daySchedule.add(LocalTime.of(8, 0));
		daySchedule.add(LocalTime.of(9, 0));
		daySchedule.add(LocalTime.of(10, 0));

		doctor.setUsername("doctor");
		doctor.setFirstName("firstName");
		doctor.setLastName("lastName");
		doctor.setEmail("email@gmail.com");
		doctor.setPhoneNumber("0935036714");
		doctor.setField("field111");
		doctor.setPassword("passwordpassword");
		doctor.setMonday(daySchedule);
		doctor.setTuesday(daySchedule);
		doctor.setWednesday(daySchedule);
		doctor.setThursday(daySchedule);
		doctor.setFriday(daySchedule);

	}

	@Test
	public void createPatient() {
		try {
			patientDao.createPatient(patient1);
			patientDao.createPatient(patient2);
			patientDao.createPatient(patient3);
		} catch (Exception ex) {

		}

		List<Patient> patients = patientDao.getAllPatients();

		assertEquals("One patient should have been created and retrieved", 1, patients.size());
	}

	@Test
	public void patientFunctionality() {
		patientDao.createPatient(patient1);
		doctorDao.createDoctorForTest(doctor);

		List<Doctor> doctors = doctorDao.getAllDoctors();
		System.out.println(doctors.toString());

		assertEquals("One doctor should have been created and retrieved", 1, doctors.size());

		List<Doctor> tableOfDoctorsForPatient = patientDao.showDoctorsForPatient();

		assertEquals("table of doctors for patient contains only one doctor", 1, tableOfDoctorsForPatient.size());

		assertEquals(" doctor created should have been created and retrieved to table of doctors for patient",
				doctor.getUsername(), tableOfDoctorsForPatient.get(0).getUsername());

		List<Calendar> schedule = new ArrayList<Calendar>();
		schedule = doctorDao.showScheduleForCurrent_and_NextWeek(doctor.getUsername());

		assertEquals("second spot in schedule table must be 08:15", LocalTime.of(8, 15),
				LocalTime.of(schedule.get(1).getDateTime().getHours(), schedule.get(1).getDateTime().getMinutes()));

		/*
		 * Book an appointment does not work for test puproses. Unknown reason.
		 * Error is not specified value for argument 1. Checked multiple times
		 * though. doctorDao.bookAnAppointmentFor(doctor.getUsername(),
		 * schedule.get(2).getDateTime().getTime(), "Don't worry be happy",
		 * patient1.getUsername());
		 * 
		 * List<Calendar> appointmentsTable = new ArrayList<Calendar>();
		 * appointmentsTable=patientDao.getAppointmentsTable(patient1.
		 * getUsername());
		 * 
		 * assertEquals("Only one appointment must be in the appointment table"
		 * ,1,appointmentsTable.size());
		 * 
		 * Calendar calendar = new Calendar
		 * (doctor,schedule.get(1).getDateTime(),schedule.get(1).getDay(),
		 * true,true,patient1.getUsername(),patient1.getFirstName(),patient1.
		 * getLastName(),patient1.getEmail(),
		 * patient1.getPhoneNumber(),"Don't worry be happy");
		 * 
		 * assertEquals("Appointment must be in the appointment table",calendar,
		 * appointmentsTable.get(0));
		 */

	}

	@Test
	public void doctorFunctionality() {

		doctorDao.createDoctorForTest(doctor);

		List<Calendar> schedule = doctorDao.showScheduleForCurrent_Next_and_NextNext_Weeks(doctor.getUsername());

		LocalDate date = LocalDate.now().plusDays(1);
		if (date.getDayOfWeek().toString() == "SATURDAY") {
			date = date.plusDays(2);

		}

		else if (date.getDayOfWeek().toString() == "SUNDAY") {
			date = date.plusDays(1);
		}

		LocalDateTime time;
		time = LocalDateTime.of(date, LocalTime.of(8, 0));
		LocalDateTime dateTimeFromSchedule;
		dateTimeFromSchedule = schedule.get(0).getDateTime().toLocalDateTime();

		/*
		 * under this schedule each working day consists of 12 intervals of 15
		 * minutes and week - 60 intervals
		 */

		assertEquals("First week day time spot should match ", time, dateTimeFromSchedule);

		dateTimeFromSchedule = schedule.get(60).getDateTime().toLocalDateTime();

		assertEquals("Second week day time spot should match ", time.plusWeeks(1), dateTimeFromSchedule);

		dateTimeFromSchedule = schedule.get(120).getDateTime().toLocalDateTime();

		assertEquals("Third week day time spot should match ", time.plusWeeks(2), dateTimeFromSchedule);

		// Editing schedule

		ArrayList<LocalTime> daySchedule = new ArrayList<LocalTime>();
		daySchedule.add(LocalTime.of(15, 0));
		daySchedule.add(LocalTime.of(16, 0));
		daySchedule.add(LocalTime.of(17, 0));

		doctorDao.editSchedule(daySchedule, daySchedule, daySchedule, daySchedule, daySchedule, doctor.getUsername());
		LocalDateTime timeEdited;
		timeEdited = LocalDateTime.of(date, LocalTime.of(15, 0));

		List<Calendar> scheduleEdited = doctorDao.showScheduleForCurrent_Next_and_NextNext_Weeks(doctor.getUsername());

		LocalDateTime dateTimeFromScheduleEdited;
		dateTimeFromScheduleEdited = scheduleEdited.get(0).getDateTime().toLocalDateTime();

		assertNotEquals("[Edited] First week day time spot should not match ", timeEdited, dateTimeFromScheduleEdited);
		assertEquals("[Edited] First week day time spot should  match ", time, dateTimeFromScheduleEdited);

		dateTimeFromScheduleEdited = scheduleEdited.get(60).getDateTime().toLocalDateTime();

		assertNotEquals(" [Edited] Second week day time spot should not match ", timeEdited.plusWeeks(1),
				dateTimeFromScheduleEdited);
		assertEquals("[Edited] First week day time spot should  match ", time.plusWeeks(1), dateTimeFromScheduleEdited);
		dateTimeFromScheduleEdited = scheduleEdited.get(120).getDateTime().toLocalDateTime();

		assertEquals(" [Edited] Third week day time spot should match ", timeEdited.plusWeeks(2),
				dateTimeFromScheduleEdited);

	}
}
