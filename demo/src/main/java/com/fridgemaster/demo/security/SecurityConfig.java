package com.fridgemaster.demo.security;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey = Keys.hmacShaKeyFor("zdtlD3JK56m6wTTgsNFhqzjqPzdtlD3JK56m6wTTgsNFhqzjqP".getBytes());

    private final UserDetailsService userDetailsService;
    private final com.fridgemaster.demo.security.AuthenticationManager authenticationManager;
    private final AuthProvider authProvider;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, AuthenticationManager authenticationManager, AuthProvider authProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.authProvider = authProvider;
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
                .addFilterBefore(new JwtTokenVerifier(authProvider), BasicAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests((requests) -> requests
                        .shouldFilterAllDispatcherTypes(false)
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/index.html", "/css/**", "/js/**", "/media/**", "/").permitAll()
                        .requestMatchers("/api/user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/recipes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/recipe/placeholder/**").permitAll()
                        .anyRequest().authenticated())
        ;
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}
