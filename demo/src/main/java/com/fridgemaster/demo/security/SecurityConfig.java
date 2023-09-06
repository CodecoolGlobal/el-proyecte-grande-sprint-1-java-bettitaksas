package com.fridgemaster.demo.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey = Keys.hmacShaKeyFor("zdtlD3JK56m6wTTgsNFhqzjqPzdtlD3JK56m6wTTgsNFhqzjqP".getBytes());

    private final UserDetailsService userDetailsService;
    private final com.fridgemaster.demo.security.AuthenticationManager authenticationManager;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, com.fridgemaster.demo.security.AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }
/*
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .addFilter(new UsernameAndPasswordFilter(authenticationManager,secretKey))
                .addFilterAfter(new JwtTokenVerifier(),UsernameAndPasswordFilter.class)
                .authorizeRequests(auth -> auth
                        .requestMatchers("/api/user/**").permitAll()
                        .requestMatchers("/api/fridges/**").hasAnyRole(Role.User.getRole(), Role.Premium_User.getRole(), Role.Admin.getRole())
                )
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
    //@Bean
    //public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
    //    return config.getAuthenticationManager();
    //}
    @Bean
    AuthenticationProvider authenticationProvider() {
       final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
       daoAuthenticationProvider.setUserDetailsService(userDetailsService);
       daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
       return daoAuthenticationProvider;
    }
}
