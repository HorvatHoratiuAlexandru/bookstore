package com.horvat.bookstore.configs.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomTokenAuthenticationFilter extends OncePerRequestFilter {

    private final Map<String, TokenAuthenticateService>tokenAuthHeaders;

    public CustomTokenAuthenticationFilter(Map<String, TokenAuthenticateService>tokenAuthHeaders){
        this.tokenAuthHeaders = tokenAuthHeaders;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        JwtAuthentication authAttempt = this.convertRequest(request);
        if(authAttempt == null) throw new AccessDeniedException("No supported JWT token header present");
        
        JwtAuthentication authResult = this.attemptAuthentication(authAttempt);
        
        if(authResult.isAuthenticated()){
            this.onSuccesfulAuthentication(authResult);
            filterChain.doFilter(request, response);
            return;
        }

        this.onUnsuccesfulAuthentication(authResult);

        return;
    }

    private JwtAuthentication convertRequest(HttpServletRequest request){
        for(String header : this.tokenAuthHeaders.keySet()){
            String token = request.getHeader(header);
            if(token != null && token.startsWith("Bearer ") && token.length() > 7) return new JwtAuthentication(token.split(" ")[1], header);
        }
        return null;
    }

    private JwtAuthentication attemptAuthentication(JwtAuthentication token){
        TokenAuthenticateService provider = this.tokenAuthHeaders.get(token.getType());

        return provider.verifyToken(token);
    }

    private void onSuccesfulAuthentication(Authentication authentication){
        log.info("Succesful Authentication for: " + authentication.getCredentials() + "\n" 
        + "With username: " + authentication.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void onUnsuccesfulAuthentication(JwtAuthentication authentication){
        SecurityContextHolder.clearContext();
        log.info("Unsuccesfull Authentication for: " + authentication.getCredentials() + "\n" 
        + "Reason: " + authentication.getStatus());

        if(authentication.getStatus() != null){
            throw new AccessDeniedException("Cause: " + authentication.getStatus());
        }else{
            throw new AccessDeniedException("There was an error when trying to authenticate please try again.");
        }
    }


}
