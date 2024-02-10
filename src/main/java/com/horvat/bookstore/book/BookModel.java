package com.horvat.bookstore.book;

import java.util.Set;

import com.horvat.bookstore.book.wishlist.WishListModel;
import com.horvat.bookstore.order.ItemModel;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "book")
@Setter
@Getter
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
    private Set<TagModel> tags;
    
    @OneToMany(mappedBy = "book")
    private Set<ItemModel> items;

    @ManyToMany(mappedBy = "books")
    private Set<WishListModel> wishlists;

    @ManyToMany
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<AuthorModel> authors;
    
}
