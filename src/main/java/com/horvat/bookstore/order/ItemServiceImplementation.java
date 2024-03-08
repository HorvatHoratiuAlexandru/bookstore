package com.horvat.bookstore.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.exceptions.BookNotFoundException;
import com.horvat.bookstore.book.exceptions.BookOutOfStockException;

@Service
public class ItemServiceImplementation implements ItemService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<ItemModel> createOrderItems(Map<Integer, Integer> orderItemsBookAndQty) {
        List<ItemModel> items = new LinkedList<>();

        for(Map.Entry<Integer,Integer> entry: orderItemsBookAndQty.entrySet()){
            Optional<BookModel> book = bookRepository.findById(entry.getKey());
            
            
            if(!book.isPresent()){
                StringBuilder sb = new StringBuilder();
                sb.append("Book with Id: ").append(entry.getKey().toString()).append(" NotFound");
                throw new BookNotFoundException(null);
            }
            
            Integer bookStock = book.get().getStock();
            if(bookStock < entry.getValue()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Book with Id: ").append(entry.getKey().toString()).append(" Stock To Low");
                throw new BookOutOfStockException(sb.toString());
            }

            ItemModel item = new ItemModel();
            item.setBook(book.get());
            item.setPrice(book.get().getPrice());
            item.setQty(entry.getValue());
            book.get().setStock(bookStock - entry.getValue());
            this.bookRepository.save(book.get());
            
            items.add(item);
        }

        return this.itemRepository.saveAll(items);
    }

}
