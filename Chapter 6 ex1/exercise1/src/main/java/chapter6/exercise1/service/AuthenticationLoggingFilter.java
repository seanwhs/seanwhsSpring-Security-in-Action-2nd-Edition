// AuthenticationLoggingFilter.java
package chapter6.exercise1.service;
import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Extending the OncePerRequestFilter class
public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

    // @Override
	// public void doFilter(
	// 	ServletRequest request,
	// 	ServletResponse response,
	// 	FilterChain filterChain)
	// 	throws IOException, ServletException {

	// 	var httpRequest = (HttpServletRequest) request;
	// 	var requestId = httpRequest.getHeader("Request-Id"); 

	// 	logger.info("Successfully authenticated request with id " + requestId); 

	// 	filterChain.doFilter(request, response); 
	// }

    @Override
	protected void doFilterInternal( 
	HttpServletRequest request, 
	HttpServletResponse response, 
	FilterChain filterChain) throws
	ServletException, IOException {

		String requestId = request.getHeader("Request-Id");
		logger.info("Successfully authenticated request with id " + requestId);
		filterChain.doFilter(request, response);
	}
}
