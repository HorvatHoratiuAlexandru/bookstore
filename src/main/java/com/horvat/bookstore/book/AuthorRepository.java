package com.horvat.bookstore.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AuthorRepository extends Repository<AuthorModel, Integer> {
    Optional<AuthorModel> findById(Integer id);
    Optional<AuthorModel> findByName(String name);
    AuthorModel save(AuthorModel author);
    @Query("SELECT a FROM AuthorModel a WHERE a.name LIKE :matchToken%")
    List<AuthorModel> search(String matchToken);
}
