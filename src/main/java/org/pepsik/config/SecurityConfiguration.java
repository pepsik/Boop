package org.pepsik.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
                .csrf()
                .and()
                .exceptionHandling()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/page/*").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/views/view2.html").authenticated()
                .antMatchers("/views/*").permitAll()
                .antMatchers("/api/page/*").permitAll()
                .antMatchers("/api/post/*").permitAll()
                .antMatchers("/api/pagination").permitAll()
//                .antMatchers("/api/activate").permitAll()
//                .antMatchers("/api/authenticate").permitAll()
//                .antMatchers("/api/account/reset_password/init").permitAll()
//                .antMatchers("/api/account/reset_password/finish").permitAll()
//                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated();
    }
}
