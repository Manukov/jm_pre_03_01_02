package com.manukov.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService detailsService;

    private final SuccessUserHandler successUserHandler;

    public SecurityConfig(UserDetailsService detailsService, SuccessUserHandler successUserHandler) {
        this.detailsService = detailsService;
        this.successUserHandler = successUserHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .authorizeRequests()
                    .antMatchers("/")
                    .permitAll()
                    .and()

                .authorizeRequests()
                .antMatchers("/webjars/**")
                .permitAll()
                .and()

                .authorizeRequests()
                    .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()

                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(successUserHandler)
                    .and()

                .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(detailsService)
                .passwordEncoder(passwordEncoder());
    }
}
