// ProjectConfig.java
package chapter5.exercise3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import chapter5.exercise3.service.StaticKeyAuthenticationFilter;

//Configuring the custom filter before and after authentication
@Configuration
public class ProjectConfig {
	private final StaticKeyAuthenticationFilter filter;
	public ProjectConfig(StaticKeyAuthenticationFilter filter){
		this.filter=filter;
	} 

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
	throws Exception {
		http
			.addFilterAt(filter, BasicAuthenticationFilter.class)
			.authorizeRequests(c -> c.anyRequest().permitAll());
		return http.build();
	}
}