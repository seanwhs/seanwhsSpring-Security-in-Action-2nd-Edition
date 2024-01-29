// ProjectConfig.java
package chapter5.exercise4.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import chapter5.exercise4.service.AuthenticationLoggingFilter;
import chapter5.exercise4.service.RequestValidationFilter;

//Configuring the custom filter before and after authentication
@Configuration
public class ProjectConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
			throws Exception {
		http
				.addFilterBefore( 
					new RequestValidationFilter(), 
					BasicAuthenticationFilter.class)
				.addFilterAfter(
						new AuthenticationLoggingFilter(),
						BasicAuthenticationFilter.class)
				.authorizeRequests(c -> c.anyRequest().permitAll());
		return http.build();
	}
}