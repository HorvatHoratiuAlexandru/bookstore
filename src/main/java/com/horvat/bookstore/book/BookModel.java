package com.horvat.bookstore.book;

import java.util.Objects;
import java.util.Set;

import com.horvat.bookstore.book.wishlist.WishListModel;
import com.horvat.bookstore.order.ItemModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_tags",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagModel> tags;
    
    @OneToMany(mappedBy = "book")
    private Set<ItemModel> items;

    @ManyToMany(mappedBy = "books")
    private Set<WishListModel> wishlists;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "authors_id")
    )
    private Set<AuthorModel> authors;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookModel book = (BookModel) obj;
        return this.title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
