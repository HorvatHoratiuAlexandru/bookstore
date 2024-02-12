package com.horvat.bookstore.book;

import java.util.Set;

import org.springframework.data.repository.Repository;
import java.util.List;


public interface BookRepository extends Repository<BookModel, Integer> {
    Set<BookModel> saveAll(Iterable<BookModel> books);
    List<BookModel> findByTitle(String title);
}