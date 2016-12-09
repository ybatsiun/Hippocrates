package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String showHome(){
		System.out.println("I am showHome() controller ! ");
		
		return "home";
	}
}
