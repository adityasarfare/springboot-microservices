package com.javaguides.com.discoveryserver.config;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	@Value("${spring.security.user.name}")
	private String username;
	
	@Value("${spring.security.user.password}")
	private String password;
	
	
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication()
		.passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser(username).password(password)
		.authorities("USER");
	}
	

	
	public void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.csrf().disable()
//		.authorizeRequests().anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
		
		httpSecurity
        .csrf(csrf -> csrf.disable())  // Disable CSRF
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()  // All requests need to be authenticated
        ).httpBasic(httpBasic -> {});  //
	}
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())  // Disable CSRF protection
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().authenticated()  // Require authentication for all requests
        )
        .httpBasic(httpBasic -> {});  // Enable basic authentication

    return http.build();

    }
}
