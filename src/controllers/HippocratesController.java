package controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.Doctor;
import dao.DoctorDao;
import dao.Patient;
import dao.PatientDao;
import service.DoctorsService;

@Controller
public class HippocratesController {

	@Autowired
	PatientDao patientDao;

	@Autowired
	DoctorDao doctorDao;
	
	@Autowired
	private DoctorsService doctorService;

	@RequestMapping("/authenticated")
	public String showAuthenticated() {

		for (Patient patient : patientDao.getAllPatients()) {
			System.out.println(patient.toString());
		}
		return "authenticated";
	}

	@RequestMapping("/doctors-registration-form")
	public String showDoctorsRegistrationForm(Model model) {

		// Introducing model
		Doctor doctor = new Doctor();
		ArrayList<LocalTime> test = new ArrayList<LocalTime>();
		test.add(LocalTime.of(8, 0));
		ArrayList<LocalTime> monday = new ArrayList<LocalTime>();

		for (int a = 8; a < 18; a++) {
			for (int b = 0; b < 60; b += 15) {

				monday.add(LocalTime.of(a, b));

			}
		}
		doctor.setMonday(test);
		model.addAttribute("doctor", doctor);
		model.addAttribute("monday", monday);

		return "doctors-registration-form";
	}

	@RequestMapping("/registration-completed")
	public String showRegistrationCompleted(Doctor doctor) {

		doctorDao.createDoctor(doctor);

		return "registration-completed";
	}

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
}