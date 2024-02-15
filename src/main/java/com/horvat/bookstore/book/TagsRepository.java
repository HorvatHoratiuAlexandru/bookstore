package com.horvat.bookstore.book;


import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.Repository;

public interface TagsRepository extends Repository<TagModel, Integer> {
    Set<TagModel> saveAll(Iterable<TagModel> tags);
    Optional<TagModel> findByName(String name);
    
}
