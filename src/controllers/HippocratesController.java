package controllers;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.Calendar;
import dao.Doctor;
import dao.Patient;
import dao.PatientDao;

@Controller
public class HippocratesController {

	@Autowired
	PatientDao patientDao;

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
		
		List <Calendar> items= new ArrayList <Calendar>(); 
		String[] days = new String[5];
		days[0] = "Monday";
		days[1] = "Tuesday";
		days[2] = "Wednesday";
		days[3] = "Thursday";
		days[4] = "Friday";

		for (int i = 0; i < 5; i++) {
			
			for (int a = 8; a < 18; a++) {
				for (int b = 0; b < 60; b += 15) {
					@SuppressWarnings("deprecation")
					Calendar calendar = new Calendar (new Timestamp(2017, 1, 2, a, b, 0, 0), days[i]);
					
					items.add(calendar);
				}
			}
			
		}
		/*for (HashMap.Entry<String, LocalDateTime> entry : schedule.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    System.out.println("Key is: " + key+" .Values is: "+ value);
		}*/
		//doctor.setSchedule(schedule);
		model.addAttribute("doctor", doctor);
		model.addAttribute("items",items);

		return "doctors-registration-form";
	}

	@RequestMapping("/choose-schedule")
	public String showChooseSchedule(Model model, Doctor doctor) {
		System.out.println(model.containsAttribute("schedule"));

		
	
		return "choose-schedule";
	}
}
