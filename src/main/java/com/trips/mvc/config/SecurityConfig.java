package com.trips.mvc.config;

import com.trips.mvc.config.auth.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(x->x.disable())
                .authorizeHttpRequests(request-> request
                        .requestMatchers("/home").hasAnyAuthority("ADMIN", "MODERATOR", "USER", "AUTHOR")
                        .requestMatchers("/admin/article/**", "/admin/trip/**").hasAnyAuthority("ADMIN", "MODERATOR")
                        .requestMatchers("/admin/article/**").hasAnyAuthority("ADMIN", "AUTHOR")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/login", "/register", "/forgot-password", "/reset-password").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(form-> form
                        .defaultSuccessUrl("/admin")
                        .loginPage("/login")
                        .failureUrl("/login")


                )
                .logout(logout->
                        logout.logoutSuccessUrl("/login")
                )
                .exceptionHandling(e->e
                        .accessDeniedPage("/login")

                );

        return http.build();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }



}
