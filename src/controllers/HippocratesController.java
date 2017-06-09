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

	/*@RequestMapping("/registration-completed-patient")
	public String showRegistrationCompletedForPatient(@Valid Patient patient, BindingResult result) {

		if (result.hasErrors()) {
			return "patients-registration-form";
		}

		else {

			patientService.createPatient(patient);

			return "registration-completed";
		}
	}*/

	@RequestMapping("/creating-doctor")
	public String creatingDoctor(@Valid Doctor doctor, BindingResult result) {

		if (result.hasErrors()) {

			return "doctors-registration-form";
		}

		else {
			doctorService.createDoctor(doctor);
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

	/*@RequestMapping("/registration-completed")
	public String showRegistrationCompleted(@ Valid Doctor doctor, BindingResult result,Model model) {
		if (result.hasErrors()) {

			return "doctors-registration-form";
		}
		else{
			doctorDao.createDoctor(doctor);
		return "registration-completed";}
	}*/

	@RequestMapping(value = "/edit-schedule")
	public String showEditSchedule(Model model, Principal principal) {

		// Introducing model
		Doctor doctor = new Doctor();
		ArrayList<LocalTime> precheckedVal = new ArrayList<LocalTime>();

		System.out.println("Introducing model");

		List<Timestamp> input = doctorService.showScheduledTimeForClosestWeek(principal.getName());

		for (int i = 0; i < input.size(); i++) {
			int minutes = input.get(i).getMinutes();
			int hours = input.get(i).getHours();

			precheckedVal.add(LocalTime.of(hours, minutes));

		}

		System.out.println("Values in the list " + precheckedVal.toString());

		ArrayList<LocalTime> monday = new ArrayList<LocalTime>();

		for (int c = 8; c < 18; c++) {
			for (int b = 0; b < 60; b += 15) {

				monday.add(LocalTime.of(c, b));

			}
		}

		doctor.setMonday(precheckedVal);
		model.addAttribute("doctor", doctor);
		model.addAttribute("monday", monday);

		return "edit-schedule";
	}

	@RequestMapping(value = "/edition-completed")
	public String showEditionCompleted(Doctor doctor, Principal principal) {

		System.out.println("New list of time " + doctor.getMonday().toString());

		doctorDao.editSchedule(doctor.getMonday(), principal.getName());

		return "edition-completed";
	}

	@RequestMapping(value = "/showDocScheduleForPatient")
	public String showDocScheduleForPatient(@RequestParam("doctorUsername") String doctorUsername, Doctor doctor,
			Principal principal) {

		System.out.println("showDocScheduleForPatient mapping");

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

	// try to write this as an action in flow to redirect to
	// /getAppointmentTable after having canceled an appointment
	@RequestMapping(value = "/cancelAnAppointment")
	public String showCancelAnAppointment(@RequestParam("id") int id) {
		patientService.cancelAnAppointment(id);

		return "showAppointmentsTable";

	}

}