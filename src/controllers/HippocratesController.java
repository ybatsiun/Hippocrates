package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HippocratesController {

	@RequestMapping("/authenticated")
	public String showAuthenticated(){
		
		return "authenticated";
	}
	
	
}
