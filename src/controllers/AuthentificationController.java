package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthentificationController {

	
	@RequestMapping("/login")
	public String ShowLogInForm() {
		System.out.println("I am login controller");
		
		
		return "login";
	}
}
