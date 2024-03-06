package com.horvat.bookstore.book;

import com.horvat.bookstore.appUser.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ReviewModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ReviewGrade grade;
    @Column
    private String text;

    @ManyToOne(targetEntity = BookModel.class)
    @JoinColumn(name = "book_id", nullable = false)
    private BookModel book;

    @ManyToOne(targetEntity = UserModel.class)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
