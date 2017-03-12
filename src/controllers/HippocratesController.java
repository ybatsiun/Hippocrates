package controllers;

import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import dao.Doctor;
import dao.DoctorDao;
import dao.Patient;
import dao.PatientDao;

@Controller
public class HippocratesController {

	@Autowired
	PatientDao patientDao;

	@Autowired
	DoctorDao doctorDao;

	@RequestMapping("/authenticated")
	public String showAuthenticated() {

		for (Patient patient : patientDao.getAllPatients()) {
			System.out.println(patient.toString());
		}
		return "authenticated";
	}

	@RequestMapping(value = "/doctors-registration-form", method = RequestMethod.GET)
	public String showDoctorsRegistrationForm(Model model) {

		// Introducing model
		Doctor doctor = new Doctor();

		ArrayList<LocalTime> monday = new ArrayList<LocalTime>();

		for (int a = 8; a < 18; a++) {
			for (int b = 0; b < 60; b += 15) {

				monday.add(LocalTime.of(a, b));

			}
		}

		model.addAttribute("doctor", doctor);
		model.addAttribute("monday", monday);

		return "doctors-registration-form";
	}
	
	@RequestMapping("/registration-completed")
	public String showEditSchedule(Doctor doctor) {

		doctorDao.createDoctor(doctor);

		

		return "registration-completed";
	}

	@RequestMapping("/edit-schedule")
	public String showEditSchedule(Model model) {

		// Introducing model
				Doctor doctor = new Doctor();
		
		
		System.out.println("Introducing model");
		
		JSONParser parser = new JSONParser();
		
		
		
		
		/*ArrayList< Doctor> schedule = new ArrayList<Doctor>(doctorDao.showSchedule(principal.getName()));
		
		
		
		//Forming list of prechecked values
		System.out.println("Forming list of prechecked values");
		ArrayList <LocalTime> time = new ArrayList<LocalTime>();
		System.out.println("0");
		for (int i=0;i<schedule.size();i++){
			System.out.println("a: " );
			Object d= schedule.get(i);
			for ( int b=0;b< 1;b++){
				
				System.out.println("b");
				Object d7=  schedule.get(0).calendar.get(b);
				int hours=d7.
				int minutes=schedule.get(i).getCalendar().get(b).getDateTime().getMinutes();
				System.out.println("c");
				time.add(LocalTime.of(hours, minutes));
			}
		}
		System.out.println("Finished forming list of prechecked values ");
		////
		
		doctor.setMonday(time);*/
		model.addAttribute("doctor", doctor);
		System.out.println("Added doctor with prechecked values to a model");
		
		ArrayList<LocalTime> monday = new ArrayList<LocalTime>();

		for (int c = 8; c < 18; c++) {
			for (int b = 0; b < 60; b += 15) {

				monday.add(LocalTime.of(c, b));

			}
		}
		
		//System.out.println("Finished forming default list of time rows");
		model.addAttribute("monday", monday);

		return "edit-schedule";
	}
}
