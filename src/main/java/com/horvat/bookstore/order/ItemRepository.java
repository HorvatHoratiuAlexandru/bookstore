package com.horvat.bookstore.order;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface ItemRepository extends Repository<ItemModel, Integer> {
    List<ItemModel> findById(Integer id);
    List<ItemModel> saveAll(Iterable<ItemModel> entities);

}
