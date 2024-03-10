package com.horvat.bookstore.book;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;




public interface BookRepository extends Repository<BookModel, Integer> {
    Set<BookModel> saveAll(Iterable<BookModel> books);
    BookModel save(BookModel book);
    List<BookModel> findByTitle(String title);
    Optional<BookModel> findById(Integer id);
    List<BookModel> findAll();
    List<BookModel> findByTagsIn(Iterable<TagModel> tags);
    @Query("SELECT b FROM book b WHERE b.title LIKE %:matchToken% OR b.description LIKE %:matchToken%")
    List<BookModel> searchBooks(String matchToken);
}