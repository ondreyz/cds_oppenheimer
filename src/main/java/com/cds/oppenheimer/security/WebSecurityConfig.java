package com.cds.oppenheimer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .csrf().disable()
            .antMatcher("/api/**")
            .authorizeRequests()
                .antMatchers("/api/workingclasshero").hasRole("CLERK")
            .anyRequest()
                .authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // For demo only
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        // Create users
        // Use password encoder instead to circumvent issue with bcrypt password encoder
        manager.createUser(users.username("clerk").password(passwordEncoder.encode("clerkpassword")).roles("CLERK").build());
        manager.createUser(users.username("bookkeeper").password(passwordEncoder.encode("bookkeeperpassword")).roles("BOOKKEEPER").build());
        manager.createUser(users.username("governor").password(passwordEncoder.encode("governorpassword")).roles("GOVERNOR").build());
        return manager;
    }
}