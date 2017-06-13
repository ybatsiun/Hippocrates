package controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@EnableWebMvc
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
/*Application runs this method when errors is thrown. This can be controlled by "Exception Handler" written
	in console, but the page is not redirected.*/
	@ResponseBody
	@ExceptionHandler(value = { Exception.class, org.springframework.dao.DataAccessException.class,
			org.hibernate.QueryException.class })
	public String handleDatabaseException(HttpServletResponse response) {
		System.out.println("Exception Handler");
		ModelAndView andView = new ModelAndView();
		// response.sendRedirect("/denied");
		return "error";
	}

	

}
