package com.horvat.bookstore.book;

import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface AuthorRepository extends Repository<AuthorModel, Integer> {
    Optional<AuthorModel> getByName(String name);
    AuthorModel save(AuthorModel author);
}
