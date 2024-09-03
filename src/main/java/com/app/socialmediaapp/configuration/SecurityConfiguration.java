package com.app.socialmediaapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.app.socialmediaapp.security.JWTAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;
import com.app.socialmediaapp.security.JWTAuthenticationFilter;
import com.app.socialmediaapp.services.UserDetailsServiceImplementation;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    private UserDetailsServiceImplementation userDetailsService;

    private JWTAuthenticationEntryPoint handler;                                                    

    public SecurityConfiguration(UserDetailsServiceImplementation userDetailsService, JWTAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JWTAuthenticationFilter JWTAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {  
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");  
        config.addAllowedHeader("*");   
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(withDefaults())
                .csrf(customizer -> customizer.disable())
                .exceptionHandling(handling -> handling.authenticationEntryPoint(handler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                	    .requestMatchers("/auth/**").permitAll()
                	    .requestMatchers(HttpMethod.GET, "/likes").permitAll()
                	    .requestMatchers(HttpMethod.GET, "/comments").permitAll()
                	    .requestMatchers("/posts/**").authenticated()  // Ensure that only authenticated users can access posts
                	    .anyRequest().authenticated()
                	);


        httpSecurity.addFilterBefore(JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); 
        return httpSecurity.build();
    }
}
