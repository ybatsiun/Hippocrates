package controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class PatientController {
	@Autowired
	private DoctorsService doctorService;

	@Autowired
	private PatientsService patientService;

	@RequestMapping("/")
	public String showHome() {
		System.out.println("I am showHome() controller ! ");

		return "home";
	}

	@RequestMapping("/patients-registration-form")
	public String showRegistrationFormForPatient(Patient patient) {

		return "patients-registration-form";
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

		
		doctorService.bookAnAppointmentFor(doctorUsername, dateTime, complain, principal.getName().toString());

		return "bookingSuccess";

	}

	@RequestMapping(value = "/getDocListForPatient", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDocListForPatient() {

		List<Doctor> doctorsList = null;

		doctorsList = patientService.showDoctorsForPatient();

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

	@RequestMapping(value = "/patientsPage")
	public String showPatientsPage() {
		System.out.println("Patient page in controller");
		return "patientsPage";

	}
	
	@RequestMapping(value = "/doctors-list-for-patient")
	public String showDoctorsListForPatient() {
		System.out.println("Patient page in controller");
		return "doctors-list-for-patient";

	}

}
