package com.roombooking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/book", "/dashboard").authenticated()  // Login required
                .anyRequest().permitAll()                                 // Everything else public
            )
            .formLogin()
                .loginPage("/login")        // your custom login page
                .defaultSuccessUrl("/dashboard", true) // redirect to dashboard after login
            .and()
            .logout()
                .logoutSuccessUrl("/");    // redirect to public home after logout

        return http.build();
    }
}