package com.lavr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by lavr on 5/30/15.
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("pass").roles("USER").and()
                .withUser("admin").password("pass").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.addFilterBefore(filter,CsrfFilter.class);

        http.formLogin()
                .loginPage("/login")
                .and()
                .rememberMe()
                .key("bookKey")
                .and()
                .logout()
                .logoutSuccessUrl("/#logout")
                .and()
                .authorizeRequests()
                .antMatchers("/crud/*").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests()
                .antMatchers("/events/*").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();
    }
}
