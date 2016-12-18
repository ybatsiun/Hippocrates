package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.Patient;
import dao.PatientDao;

@Controller
public class HippocratesController {

	
	@Autowired
	PatientDao patientDao;
	
	@RequestMapping("/authenticated")
	public String showAuthenticated(){
		
		
		for (Patient patient : patientDao.getAllPatients()){
			System.out.println(patient.toString());
		}
		return "authenticated";
	}
	
	
}
