package org.pepsik.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    private EntryPointUnauthorizedHandler unauthorizedHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bower_components/**")
                .antMatchers("/js/**")
                .antMatchers("/css/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/components/**")
                .antMatchers("/uploads/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/vendor/**", "/assets/*", "/src/**", "/fonts/**").permitAll()
                .antMatchers("/*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/posts/**").permitAll()
                .antMatchers("/rest/accounts*").permitAll()
                .antMatchers("/**").authenticated();
    }
}
