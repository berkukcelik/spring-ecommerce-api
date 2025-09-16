package com.example.ecommerceapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// spring otomatik tarar ve beanleri ioc'a ekler
@Configuration
// spring security aktif hale getirmek ve filter'lar ekleme icin
@EnableWebSecurity
public class SecurityConfig {

    // security filter chain icin:
    // istek gelir -> JwtFilter
   @Autowired
   private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http parametresi ile yeni filtreler ekleyebiliriz
        http.csrf(csrf->csrf.disable());
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // permit all ile auth endpointine izin verdik kalan requestler authenticated olmak zorunda
                .authorizeHttpRequests(auth->auth
                        // test icin diger endpointlere permitall dedim
                        .requestMatchers("/api/auth/**","/api/shop/**",
                                "/api/product/**","/api/category/**").permitAll()
                        .anyRequest().authenticated());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // filtreyi derleyip ekler
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}