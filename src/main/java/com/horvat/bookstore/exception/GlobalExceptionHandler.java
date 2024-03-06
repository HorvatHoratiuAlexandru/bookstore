package com.horvat.bookstore.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.horvat.bookstore.appUser.exceptions.DontOwnTheResourceException;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;
import com.horvat.bookstore.book.exceptions.AlreadyReviewedException;
import com.horvat.bookstore.book.exceptions.BadReviewGrade;
import com.horvat.bookstore.book.exceptions.BookNotFoundException;
import com.horvat.bookstore.book.exceptions.BookOutOfStockException;
import com.horvat.bookstore.book.wishlist.exception.ItemAlreadyInTheWishList;
import com.horvat.bookstore.order.exception.OrderNotFound;
import com.horvat.bookstore.promoCode.exception.PromoCodeAlreadyUsed;
import com.horvat.bookstore.promoCode.exception.PromoCodeExpired;
import com.horvat.bookstore.promoCode.exception.PromoCodeNotFound;

import lombok.extern.log4j.Log4j2;


@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFountException(UserNotFoundException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFountException(BookNotFoundException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BookOutOfStockException.class)
    public ResponseEntity<String> handleBookOutOfStockException(BookOutOfStockException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PromoCodeNotFound.class)
    public ResponseEntity<String> handlePromoCodeNotFound(PromoCodeNotFound ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PromoCodeExpired.class)
    public ResponseEntity<String> handlePromoCodeExpired(PromoCodeExpired ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(PromoCodeAlreadyUsed.class)
    public ResponseEntity<String> handlePromoCodeAlreadyUsed(PromoCodeAlreadyUsed ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<String> handleOrderNotFound(OrderNotFound ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ItemAlreadyInTheWishList.class)
    public ResponseEntity<String> handleItemAlreadyInTheWishList(ItemAlreadyInTheWishList ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgNotValid(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(DontOwnTheResourceException.class)
    public ResponseEntity<String> handleDontOwnTheResourceException(DontOwnTheResourceException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BadReviewGrade.class)
    public ResponseEntity<String> handleBadReviewGradeException(BadReviewGrade ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyReviewedException.class)
    public ResponseEntity<String> handleAlreadyReviewedException(AlreadyReviewedException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
