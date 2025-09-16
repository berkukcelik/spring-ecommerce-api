package com.example.ecommerceapi.security;

import com.example.ecommerceapi.Service.JwtService;
import com.example.ecommerceapi.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// her istekte filtre calisir
// authorization header'ını kontrol edecek token var ise JwtServisimiz doğrulayacak
// yoksa SecurityContexte depolaması icin verecek
// SecurityContext -> dogrulanmıs kullanıcıların saklandigi kisim .


@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal (
         @NonNull HttpServletRequest request ,
         @NonNull HttpServletResponse response ,
         @NonNull FilterChain filterChain) throws ServletException , IOException {

        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            // bearer ile baslamıyosa veya null ise sonraki filtreden istegi devam ettir.
            filterChain.doFilter(request, response);
            return;
        }
        final String token = header.substring(7);
        // kullancı adı varsa ve securitycontext bos ise
         String username = jwtService.extractUsername(token);
         if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
             // kullanici bilgilerini contexte yükle
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);

             if(jwtService.isTokenValid(token,userDetails)) {
                 // auth objesi
                 UsernamePasswordAuthenticationToken authToken =
                         new  UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }
         }

        filterChain.doFilter(request, response);
    }

}