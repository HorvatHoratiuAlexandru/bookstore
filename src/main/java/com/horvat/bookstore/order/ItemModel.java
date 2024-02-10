package com.horvat.bookstore.order;

import com.horvat.bookstore.book.BookModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "items")
public class ItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer qty;
    @Column
    private Float price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;
    
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book;

}
