package com.horvat.bookstore.configs.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.horvat.bookstore.appUser.exceptions.DontOwnTheResourceException;
import com.horvat.bookstore.configs.security.JwtAuthentication;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Aspect
@Component
public class CheckUserUidAspect {

    @Around("@annotation(CustomLoggedInUserIdMatch)")
    public Object checkUserUidBeforeMethodExecute(ProceedingJoinPoint joinPoint) throws Throwable{
        JwtAuthentication currentUser = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();

        if(!currentUser.isAuthenticated()){
            //should not trigger the authentication filter should handle it
            throw new AccessDeniedException("UserNotAuthenticated");
        }

        String loggedInUserUid = currentUser.getUserUID();
        log.info("Authenticated user:" + loggedInUserUid);
        boolean isUsersRequestedResource = false;
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            log.info("arg:" + arg.toString());
            if(arg.toString().equals(loggedInUserUid)){
                isUsersRequestedResource = true;
            }
        }

        if(!isUsersRequestedResource){
            throw new DontOwnTheResourceException("You don't own the requested resource");
        }
        
        return joinPoint.proceed();
        
    }

}
