package controllers;

import java.security.Principal;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.Calendar;
import dao.Doctor;
import dao.Patient;
import service.DoctorsService;
import service.PatientsService;

@Controller
public class HomeController {
	@Autowired
	private DoctorsService doctorService;
	
	@Autowired
	private PatientsService patientService;

	@RequestMapping("/")
	public String showHome() {
		System.out.println("I am showHome() controller ! ");

		return "home";
	}

	@RequestMapping(value = "/getDoctors-schedule", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> ShowDoctorsSchedule(Principal principal, Authentication a) {

		List<Calendar> schedule = null;
		if (principal == null) {
			schedule = null;
		}

		else {
			for (GrantedAuthority grantedAuthority : a.getAuthorities()) {
			System.out.println("Authority is "+grantedAuthority );
				if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")  ) {
					System.out.println("This is admin");
				//	schedule = doctorService.showSchedules();//!!!
				}

				else {
					String username = principal.getName();
					System.out.println("Got username at controller:" + username);
					schedule = doctorService.showDoctorsScheduleForNextMonth(username);
					System.out.println("Finished running doctorService.showSchedule(username)");

				}
			}

		}
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("schedule", schedule);
		System.out.println("Placed list schedule list in Map");

		return data;
	}

	@RequestMapping(value = "/getDoctors-list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> ShowDoctors() {

		List<Doctor> doctorsList = null;

		doctorsList = doctorService.showDoctorsList();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("doctorsList", doctorsList);

		return data;
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/bookingAppointment", method = RequestMethod.POST)
	public void bookAnAppointment(@RequestParam("doctorUsername") String doctorUsername,
			@RequestParam("dayAndTime") String dayAndTime, @RequestParam("complain") String complain,
			Principal principal) {
		System.out.println(
				"I am bookAnAppointment Controller with" + doctorUsername + " and " + dayAndTime + " and " + complain);
		String patientUsername = principal.getName();

		System.out.println("Going to doctorService in bookAnAppointment");
		doctorService.bookAnAppointment(doctorUsername, patientUsername, dayAndTime, complain);
	}

	@RequestMapping(value = "/getDoctors-list-for-admin", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> ShowDoctorsForAdmin() {

		List<Doctor> doctorsList = null;

		doctorsList = doctorService.showDoctorsListForAdmin();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("doctorsList", doctorsList);

		return data;
	}
	
	@RequestMapping(value = "/getPatients-list-for-admin", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> ShowPatientsForAdmin() {

		List<Patient> patientsList = null;

		patientsList = patientService.showPatientsListForAdmin();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("patientsList", patientsList);

		return data;
	}
	
	@ResponseBody
	@RequestMapping(value = "/testingJSON", method = RequestMethod.POST)
	public void test(@RequestParam("input") String isScheduled,
			@RequestParam("day") String day, @RequestParam("time") LocalTime time) {
		
System.out.println("Hello");		
		
		
	}

}
