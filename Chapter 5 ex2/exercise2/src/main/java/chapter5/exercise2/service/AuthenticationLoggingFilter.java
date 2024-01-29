// AuthenticationLoggingFilter.java
package chapter5.exercise2.service;
import java.io.IOException;
import java.util.logging.Logger;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

//Defining a filter to log requests
public class AuthenticationLoggingFilter implements Filter {
    private final Logger logger = Logger.getLogger(
            AuthenticationLoggingFilter.class.getName());

    @Override
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain filterChain)
		throws IOException, ServletException {

		var httpRequest = (HttpServletRequest) request;
		var requestId =
		httpRequest.getHeader("Request-Id"); 
		
		logger.info("Successfully authenticated request with id " + requestId); 
		
		filterChain.doFilter(request, response); 
	}
}