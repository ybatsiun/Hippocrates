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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
public class DoctorController {

	

	

	@Autowired
	private DoctorsService doctorService;

	@Autowired
	private PatientsService patientService;

	@RequestMapping("/doctors-registration-form")
	public String showDoctorsRegistrationForm(Doctor doctor) {

		return "doctors-registration-form";
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

	
/*shows a schedule for nextnext week (checked checkboxes) . Gets list of time stamps from the 
 * showScheduleTimeForNextNextWeek method and the depending on the week day distributes it among
 * 5 Local time lists for each day. Each of this lists is passed to the doctor object and this object is then
 * added to a model. This model is shown in the JSP*/
	@RequestMapping(value = "/edit-schedule")
	public String showEditSchedule(Model model, Principal principal) {

		Doctor doctor = doctorService.getDoctorByUsername(principal.getName());

		List<Timestamp> input = doctorService.showScheduleTimeForNextNextWeek(principal.getName().toString());
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

			doctorService.editSchedule(doctor.getMonday(), doctor.getTuesday(), doctor.getWednesday(), doctor.getThursday(),
					doctor.getFriday(), principal.getName());

			return "edition-completed";
		}
	}

	@RequestMapping(value = "/edition-completed")
	public String showEditionCompleted(Doctor doctor, Principal principal) {

		System.out.println("New list of time " + doctor.getMonday().toString());

		doctorService.editSchedule(doctor.getMonday(), doctor.getTuesday(), doctor.getWednesday(), doctor.getThursday(),
				doctor.getFriday(), principal.getName());

		return "edition-completed";
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
	
	@RequestMapping(value = "/getDoctors-list", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> ShowDoctors() {

		List<Doctor> doctorsList = null;

		doctorsList = patientService.showDoctorsForPatient();

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("doctorsList", doctorsList);

		return data;
	}
	
	@RequestMapping(value = "/getDoctors-schedule", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDoctorsSchedule(Principal principal, Authentication a) {
		System.out.println(doctorService.getDoctorByUsername(principal.getName()).toString());
		List<Calendar> schedule = null;
		if (principal == null) {
			schedule = null;
		}

		else {
			
					
					String username = principal.getName();
					System.out.println("Got username at controller:" + username);
					schedule = doctorService.showScheduleForCurrent_Next_and_NextNext_Weeks(username);

		}
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("schedule", schedule);
		System.out.println("Placed list schedule list in Map");

		return data;
	}
	
	@RequestMapping(value = "/getDocScheduleForPatient", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public Map<String, Object> getDoctorScheduleForPatient(@RequestParam("doctorUsername") String docUsername
			) {
		
		List<Calendar> calendarsList = null;

		calendarsList = doctorService.showScheduleForCurrent_and_NextWeek(docUsername);

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("calendarsList", calendarsList);

		return data;
	}
	
	@RequestMapping(value = "/doctorsPage")
	public String showDoctorsPage() {
		return "doctorsPage";

	}
	
	@RequestMapping(value = "/doctors-schedule")
	public String showDocSchedule() {
		return "doctors-schedule";

	}

}