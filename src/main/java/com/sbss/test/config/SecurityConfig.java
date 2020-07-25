package com.sbss.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2,/h2/**").permitAll()
                .antMatchers(HttpMethod.GET, "/profiles").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/profiles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/profiles").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        ;
    }

}
