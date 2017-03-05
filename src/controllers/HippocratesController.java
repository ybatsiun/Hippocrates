package controllers;

import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String showAuthenticated(){
		
		
		for (Patient patient : patientDao.getAllPatients()){
			System.out.println(patient.toString());
		}
		return "authenticated";
	}
	
	@RequestMapping(value = "/doctors-registration-form", method = RequestMethod.GET)
	public String showDoctorsRegistrationForm(Model model) {

		// Introducing model
		Doctor doctor = new Doctor();
		
		ArrayList <LocalTime> monday= new ArrayList <LocalTime>(); 
	
	

	
			
			for (int a = 8; a < 18; a++) {
				for (int b = 0; b < 60; b += 15) {
					
					monday.add(LocalTime.of(a, b));
					
				}
			}
			
		
	
		model.addAttribute("doctor", doctor);
		model.addAttribute("monday",monday);

		return "doctors-registration-form";
	}

	@RequestMapping("/choose-schedule")
	public String showChooseSchedule( Doctor doctor) {
		

		doctorDao.createDoctor(doctor);
	
	
		return "choose-schedule";
	}	
}
