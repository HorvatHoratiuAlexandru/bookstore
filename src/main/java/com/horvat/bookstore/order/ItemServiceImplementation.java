package com.horvat.bookstore.order;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;

@Service
public class ItemServiceImplementation implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<ItemModel> createOrderItems(Map<Integer, Integer> orderItemsBookAndQty) {
        List<ItemModel> items = new LinkedList<>();

        for(Map.Entry<Integer,Integer> entry: orderItemsBookAndQty.entrySet()){
            Optional<BookModel> book = bookRepository.findById(entry.getKey());
            // TODO: Throw book does not exists
            if(!book.isPresent()) return null;
            // TODO: Throw there are not X books on stock
            if(book.get().getStock() < entry.getValue()) return null;

            ItemModel item = new ItemModel();
            item.setBook(book.get());
            item.setPrice(book.get().getPrice());
            item.setQty(entry.getValue());
            
            items.add(item);
        }

        return items;
    }

}
