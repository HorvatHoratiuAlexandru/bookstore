package com.horvat.bookstore.book;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface TagsRepository extends Repository<TagModel, Integer> {
    Set<TagModel> saveAll(Iterable<TagModel> tags);
    Optional<TagModel> findByName(String name);
    Optional<TagModel> findById(Integer id);
    List<TagModel> findAll();

    @Query("SELECT t FROM TagModel t WHERE t.name LIKE :matchToken%")
    List<TagModel> search(String matchToken);
    
}
