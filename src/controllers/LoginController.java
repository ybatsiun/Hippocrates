package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	
	@RequestMapping("/login")
	public String ShowLogInForm() {
		System.out.println("I am login controller");
		
		
		return "login";
	}
	@RequestMapping("/logout")
	public String ShowLogOut() {
		System.out.println("I am logout controller");
		
		
		return "logout";
	}
	@RequestMapping("/denied")
	public String ShowDenied() {
		System.out.println("I am denied controller");
		
		
		return "denied";
	}
}
