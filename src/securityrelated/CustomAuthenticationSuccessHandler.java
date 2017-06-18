package securityrelated;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("I am onAuthenticationSuccess()");

		/* HttpSession session = httpServletRequest.getSession(); */
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		try {
		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println("Authority:" + grantedAuthority);

			  if (grantedAuthority.getAuthority().equals("ROLE_DOCTOR")) {
				httpServletResponse.sendRedirect("doctorsPage");

			} else if (grantedAuthority.getAuthority().equals("ROLE_PATIENT")) {
				httpServletResponse.sendRedirect("patientsPage");

			} 
		}
			
			/*else {
				System.out.println("Cannot identify the authority");
				throw new IllegalStateException();*/
			} catch (Exception e){
				e.printStackTrace();
		}

	}
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
