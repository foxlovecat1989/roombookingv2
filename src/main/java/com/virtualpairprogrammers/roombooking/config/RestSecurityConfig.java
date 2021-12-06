package com.virtualpairprogrammers.roombooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("matt").password("{noop}password").authorities("ROLE_ADMIN")
                .and()
                .withUser("jane").password("{noop}password").authorities("ROLE_USER");
        // TODO: this password should be encoded
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/basicAuth/**").permitAll()
                .antMatchers("/api/basicAuth/**").hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/bookings/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/api/v1/users/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAuthenticationAndAuthorizationFilter(authenticationManager()));
    }
}
