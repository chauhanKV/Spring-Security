package com.example.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Compares password after encoding
        // This provides one way encryption . It does not provide decrypt method.
        // It encodes and stores the encoded password in the DB and matches actual password after encoding with db encoded one.
        return new BCryptPasswordEncoder();

        // compares the password with plain text without encoding [No recommended]
        // return NoOpPasswordEncoder.getInstance();
    }
//
//    public static void main(String[] args) {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("nandan@123"));
//        System.out.println(passwordEncoder.encode("nisha@123"));
//    }

    // We can provide implementation for the interface "UserDetailsService" - to retrieve and check credentials from DB or any other source.
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user1Details = User.builder()
//                .username("nandan")
//                .password("$2a$10$pRCIaQFyKeUP1tE2q0Fqc.RvRX29bdy8Jxl/hqhQarCJVHjSLwPsS")
//                .build();
//
//        UserDetails user2Details = User.builder()
//                .username("nisha")
//                .password("$2a$10$0E371zqTCKTfBBnp.SGageGvNR72lol77RliDrNUJvhiZbJdu0qBi")
//                .build();
//
//        // this is inMemory store where user details are stored. JVM inMemory
//        return new InMemoryUserDetailsManager(user1Details, user2Details);
//    }


    // This method is used to filter out endpoints which do not require login.
    // This runs before the controller

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                        //.csrf((httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()))
                        .authorizeHttpRequests((auth) -> {auth.requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/common/**").permitAll()
                        .anyRequest().authenticated();
        })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
