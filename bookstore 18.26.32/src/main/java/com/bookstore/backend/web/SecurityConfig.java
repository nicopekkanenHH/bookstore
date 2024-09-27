package com.bookstore.backend.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.beans.factory.annotation.Autowired;
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService myDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final AntPathRequestMatcher[] WHITE_LIST_URLS = { 
        new AntPathRequestMatcher("/books"),
        new AntPathRequestMatcher("/h2-console/**"),
        new AntPathRequestMatcher("/"),
        new AntPathRequestMatcher("/login"),
        new AntPathRequestMatcher("/resources/**")
             };
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(WHITE_LIST_URLS).permitAll()
                .anyRequest().authenticated() 
            )
            
            .formLogin(formLogin -> formLogin
                .defaultSuccessUrl("/booklist",true) 
                .failureUrl("/login?error")
                .permitAll() 
            )
            .logout(logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll() 
            )
                .csrf(csrf -> csrf.disable()  
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions 
                .disable())  
            );

            
           

        return http.build(); 
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myDetailsService).passwordEncoder(passwordEncoder);
    }
}
