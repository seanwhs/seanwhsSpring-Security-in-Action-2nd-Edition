// CustomEntryPoint.java
package chapter6.exercise6.config;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Implementing an AuthenticationEntryPoint
public class CustomEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse,
		AuthenticationException e)
		throws IOException, ServletException {
			httpServletResponse
				.addHeader("message", "Luke, I am your father!");
			httpServletResponse
				.sendError(HttpStatus.UNAUTHORIZED.value());
	}
}