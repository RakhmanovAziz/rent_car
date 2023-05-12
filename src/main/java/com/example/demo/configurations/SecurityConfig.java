package com.example.demo.configurations;


import com.example.demo.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;

     @Autowired
     public SecurityConfig(PersonDetailsService personDetailsService) {
         this.personDetailsService= personDetailsService;
     }



    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/hello", "/registration", "/error").permitAll()
                        .anyRequest().authenticated()
                        //.requestMatchers("/**")
                        //.hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/index", true)
                        .failureUrl("/login?error")
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }






    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(personDetailsService);
    }



    @Bean
    public PasswordEncoder getPasswordEncoder() {
         return NoOpPasswordEncoder.getInstance();
    }


    /*
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
     */



}