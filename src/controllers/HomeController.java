package controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.Doctor;
import dao.DoctorDao;
import service.DoctorsService;

@Controller
public class HomeController {
	@Autowired
	private DoctorsService doctorService; 
	@Autowired
	private DoctorDao doctorDao;
	@RequestMapping("/")
	public String showHome(){
		System.out.println("I am showHome() controller ! ");
		
		return "home";
	}
	
	@RequestMapping(value="/getDoctors-schedule", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> ShowDoctorsSchedule(Principal principal){
	
		List<Doctor> schedule = null;
		if (principal==null){
			schedule = new ArrayList<Doctor>();
		}
		
		else {
			String username = principal.getName();
			System.out.println("Got username at controller:"+username);
			schedule=doctorService.showSchedule(username);
			/*schedule=doctorDao.showSchedule(username);*/
			System.out.println("Finished running doctorService.showSchedule(username)");
		}
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("schedule",schedule);
		System.out.println("Placed list schedule list in Map");
		
		
		return data;
	}
}
