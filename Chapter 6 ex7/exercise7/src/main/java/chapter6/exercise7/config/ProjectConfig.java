// ProjectConfig.java
package chapter6.exercise7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    // Set Form Login
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http)
    // throws Exception {
    // http.formLogin(c -> c.defaultSuccessUrl("/hello", true));
    // http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
    // return http.build();
    // }

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public ProjectConfig(
            CustomAuthenticationSuccessHandler authenticationSuccessHandler,
            CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public UserDetailsService uds() {
        var uds = new InMemoryUserDetailsManager();
        uds.createUser(
                User.withDefaultPasswordEncoder()
                        .username("john")
                        .password("12345")
                        .authorities("read")
                        .build());
        uds.createUser(
                User.withDefaultPasswordEncoder()
                        .username("bill")
                        .password("12345")
                        .authorities("write")
                        .build());
        return uds;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http)
            throws Exception {
        http.formLogin(c -> c
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler));

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
        return http.build();
    }
}