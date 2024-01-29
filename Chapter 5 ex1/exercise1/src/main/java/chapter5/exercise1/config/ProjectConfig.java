// ProjectConfig.java
package chapter5.exercise1.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import chapter5.exercise1.service.RequestValidationFilter;

//Configuring the custom filter before authentication
@Configuration
public class ProjectConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception {
			http.addFilterBefore( 
				new RequestValidationFilter(), 
				BasicAuthenticationFilter.class)
					.authorizeRequests(c -> c.anyRequest().permitAll());
		return http.build();
	}
}