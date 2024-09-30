package com.javaguides.apigateway.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain  springSecurityFilterChain( ServerHttpSecurity serverHttpSecurity){
		
		serverHttpSecurity.csrf(csrf -> csrf.disable())
		.authorizeExchange(exchange -> exchange
				.pathMatchers("/eureka/**")
				.permitAll()
				.anyExchange()
				.authenticated())
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults()));
		
		return serverHttpSecurity.build();
	}
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Only allow specific origin (http://localhost:4200)
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); 
        
        // Specify allowed methods
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

        // Optionally, allow specific headers if needed
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        
        // Allow credentials if necessary
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
	

	
}
