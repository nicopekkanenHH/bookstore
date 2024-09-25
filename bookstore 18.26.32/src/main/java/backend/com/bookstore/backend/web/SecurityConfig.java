package backend.com.bookstore.backend.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/register").permitAll() 
                .requestMatchers("/booklist", "/addbook", "/editbook/**", "/deletebook/**").authenticated() 
                .anyRequest().permitAll() 
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .permitAll() 
            )
            .logout(logout -> logout
                .permitAll() 
            );

        return http.build(); 
    }

}
