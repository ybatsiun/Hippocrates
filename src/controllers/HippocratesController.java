package controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.Calendar;
import dao.Doctor;
import dao.DoctorDao;
import dao.Patient;
import dao.PatientDao;
import service.DoctorsService;
import service.PatientsService;

@Controller
public class HippocratesController {

	@Autowired
	PatientDao patientDao;

	@Autowired
	DoctorDao doctorDao;

	@Autowired
	private DoctorsService doctorService;

	@Autowired
	private PatientsService patientService;

	@RequestMapping("/authenticated")
	public String showAuthenticated() {

		for (Patient patient : patientDao.getAllPatients()) {
			System.out.println(patient.toString());
		}
		return "authenticated";
	}

	@RequestMapping("/doctors-registration-form")
	public String showDoctorsRegistrationForm(Doctor doctor) {

		return "doctors-registration-form";
	}

	@RequestMapping("/patients-registration-form")
	public String showRegistrationFormForPatient(Patient patient) {

		return "patients-registration-form";
	}

	@RequestMapping("/creating-doctor")
	public String creatingDoctor(@Valid Doctor doctor, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("errors while creating doctor" + result.toString());
			return "doctors-registration-form";
		}

		else {
			doctorService.createDoctor(doctor);
			System.out.println("Doctor to create has a schedule for \n monday " + doctor.getMonday().toString()
					+ "\n tuesday " + doctor.getTuesday().toString());
			return "registration-completed";
		}
	}

	@RequestMapping("/creating-patient")
	public String creatingPatient(@Valid Patient patient, BindingResult result) {

		if (result.hasErrors()) {

			return "patients-registration-form";
		}

		else {
			patientService.createPatient(patient);
			return "registration-completed";
		}
	}

	@RequestMapping(value = "/edit-schedule")
	public String showEditSchedule(Model model, Principal principal) {

		Doctor doctor = doctorDao.getDoctorByUsername(principal.getName());

		List<Timestamp> input = doctorDao.showScheduleTimeForNextNextWeek(principal.getName().toString());
		ArrayList<LocalTime> monday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> tuesday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> wednesday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> thursday = new ArrayList<LocalTime>();
		ArrayList<LocalTime> friday = new ArrayList<LocalTime>();
		System.out.println("Input is " + input.toString());
		for (Timestamp time : input) {
			if (time.getDay() == 1) {
				monday.add(LocalTime.of(time.getHours(), time.getMinutes()));
			}

			if (time.getDay() == 2) {
				tuesday.add(LocalTime.of(time.getHours(), time.getMinutes()));
			}

			if (time.getDay() == 3) {
				wednesday.add(LocalTime.of(time.getHours(), time.getMinutes()));
			}

			if (time.getDay() == 4) {
				thursday.add(LocalTime.of(time.getHours(), time.getMinutes()));
			}

			if (time.getDay() == 5) {
				friday.add(LocalTime.of(time.getHours(), time.getMinutes()));
			}
		}
		doctor.setMonday(monday);
		doctor.setTuesday(tuesday);
		doctor.setWednesday(wednesday);
		doctor.setThursday(thursday);
		doctor.setFriday(friday);
		
		model.addAttribute("doctor", doctor);

		return "edit-schedule";
	}

	@RequestMapping(value = "/editing")
	public String editing(@Valid Doctor doctor,  BindingResult result,Principal principal) {
		



		if (result.hasErrors()) {
			System.out.println("errors while editing doctor schedule" + result.toString());
			return "edit-schedule";
		}

		else {
		System.out.println("New list of time " + doctor.getMonday().toString());

			doctorDao.editSchedule(doctor.getMonday(), doctor.getTuesday(), doctor.getWednesday(), doctor.getThursday(),
					doctor.getFriday(), principal.getName());

			return "edition-completed";
		}
	}

	@RequestMapping(value = "/edition-completed")
	public String showEditionCompleted(Doctor doctor, Principal principal) {

		System.out.println("New list of time " + doctor.getMonday().toString());

		doctorDao.editSchedule(doctor.getMonday(), doctor.getTuesday(), doctor.getWednesday(), doctor.getThursday(),
				doctor.getFriday(), principal.getName());

		return "edition-completed";
	}

	@RequestMapping(value = "/showDocScheduleForPatient")
	public String showDocScheduleForPatient(@RequestParam("doctorUsername") String doctorUsername, Doctor doctor,
			Principal principal) {

		System.out.println("showDocScheduleForPatient mapping ???");

		return "doctors-schedule-for-patient";
	}

	@RequestMapping(value = "/showBookAnAppointmentFor")
	public String showBookAnAppointmentFor() {

		return "showBookAnAppointmentFor";
	}

	@RequestMapping(value = "/bookAnAppointmentFor")
	public String bookAnAppointmentFor(@RequestParam("doctorUsername") String doctorUsername,
			@RequestParam("dateTime") long dateTime, @RequestParam("complain") String complain, Principal principal) {

		System.out.println("Booking an appointment for " + doctorUsername + " on " + dateTime + " with complain:"
				+ complain + " Patient: " + principal.getName().toString());
		doctorService.bookAnAppointmentFor(doctorUsername, dateTime, complain, principal.getName().toString());

		return "bookingSuccess";

	}

	@RequestMapping(value = "/getDocListForPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDocListForPatient() {

		List<Doctor> doctorsList = null;

		doctorsList = patientDao.showDoctorsForPatient();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("doctorsList", doctorsList);

		return data;
	}

	@RequestMapping(value = "/showAppointmentsTable")
	public String showBookingSuccess() {

		return "showAppointmentsTable";

	}

	@RequestMapping(value = "/getAppointmentTable", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getAppointmentTable(Principal principal) {
		String patientUserName = principal.getName();
		List<Calendar> calendarList = null;
		calendarList = patientService.getAppointmentsTable(patientUserName);

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("calendarList", calendarList);

		return data;

	}

	@RequestMapping(value = "/cancelAnAppointment")
	public String showCancelAnAppointment(@RequestParam("id") int id) {
		patientService.cancelAnAppointment(id);

		return "showAppointmentsTable";

	}
	
	@RequestMapping(value = "/getDoctorDetails", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List <Doctor> getDoctorDetails(Principal principal) {
		String doctorUserName = principal.getName();
		List<Doctor> doctorDetailsList = null;
		doctorDetailsList = doctorService.getDoctorDetails(doctorUserName);
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("doctorDetailsList", doctorDetailsList);

		return doctorDetailsList;

	}

}