// CustomAuthenticationFailureHandler.java
package chapter6.exercise7.config;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Implementing an AuthenticationFailureHandler
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(
	HttpServletRequest httpServletRequest,
	HttpServletResponse httpServletResponse,
	AuthenticationException e) {

		try {
			httpServletResponse.setHeader("failed", LocalDateTime.now().toString());
			httpServletResponse.sendRedirect("/showError");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
}