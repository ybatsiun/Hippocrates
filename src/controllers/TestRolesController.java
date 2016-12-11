package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestRolesController {

	
	@RequestMapping("/adminsPage")
	public String showAdminsPage() {
		return "adminsPage";
	}
	
	@RequestMapping("/doctorsPage")
	public String showDoctorsPage() {
		return "doctorsPage";
	}
	
	@RequestMapping("/patientsPage")
	public String showPatientsPage() {
		return "patientsPage";
	}
}
