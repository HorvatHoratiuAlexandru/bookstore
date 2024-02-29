package com.horvat.bookstore.configs.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class CustomAuthenticationErrorHandlingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
            return;
        } catch (AuthenticationException authException) {
            if (response.isCommitted()) {
                log.info("Did not write to response since already committed");
                return;
            }
        
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = "{\"error\": \"" + authException.getClass().getSimpleName() + "\", "
                    + "\"message\": \"" + authException.getMessage() + "\"}";
            response.getWriter().write(json);
        } catch (Exception e){
            throw e;
        }

        return;
    }

}
