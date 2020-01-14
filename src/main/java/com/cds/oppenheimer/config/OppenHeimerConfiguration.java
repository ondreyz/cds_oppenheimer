package com.cds.oppenheimer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class OppenHeimerConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        // For demo only
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        
        // Create users
        // Use password encoder instead to circumvent issue with bcrypt password encoder
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        manager.createUser(users.username("clerk").password(passwordEncoder.encode("clerkpassword")).roles("CLERK").build());
        manager.createUser(users.username("bookkeeper").password(passwordEncoder.encode("bookkeeperpassword")).roles("BOOKKEEPER").build());
        manager.createUser(users.username("governor").password(passwordEncoder.encode("governorpassword")).roles("GOVERNOR").build());
        return manager;
    }
}