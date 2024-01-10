package com.task.taskManagement.security;

import com.task.taskManagement.security.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig {


    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserDetailsServiceImplementation userDetailsServiceImplementation;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(formlogin->formlogin.permitAll());
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
        httpSecurity.authorizeHttpRequests(authorize->authorize.requestMatchers("/api/user/**").hasAuthority("USER"));
        httpSecurity.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());
        httpSecurity.userDetailsService(userDetailsServiceImplementation);
        httpSecurity.csrf(c->c.disable());
        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("ADMIN").build()

        );
    }


}
