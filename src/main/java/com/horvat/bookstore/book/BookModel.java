package com.horvat.bookstore.book;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Integer pageNumber;
    @Column
    private Float price;
    @Column
    private Integer stock;

    @ElementCollection(targetClass = TagModel.class)
    @Enumerated(EnumType.STRING)
    private List<TagModel> tags;
    
    
}
