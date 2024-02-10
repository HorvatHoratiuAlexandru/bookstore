package com.horvat.bookstore.book;

import java.util.Set;

import org.springframework.data.repository.Repository;

public interface BookRepository extends Repository<BookModel, Integer> {
    Set<BookModel> saveAll(Iterable<BookModel> books);

}