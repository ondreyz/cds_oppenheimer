package com.cds.oppenheimer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class MultiHttpSecurityConfig {

    @Order(1)
    @Configuration
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        
        @Autowired
        UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
            .httpBasic()
            .and()
            .csrf().disable()
            .antMatcher("/api/**")
            .authorizeRequests()
                .antMatchers("/api/workingclasshero").hasRole("CLERK")
                .antMatchers("/api/workingclassheroes").hasRole("CLERK")
            .anyRequest()
                .authenticated();
        }
    }

    @Order(2)
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/home").authenticated()
                    .antMatchers("/clerk").hasRole("CLERK")
                .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                .logout()
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                .and()
                    .exceptionHandling();
        }
        
    }
}
