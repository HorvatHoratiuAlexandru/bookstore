package com.horvat.bookstore.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    // return all books or all books based on tags
    @GetMapping
    public List<ResBookDto> filterBooks(@RequestParam(value = "tags", required = false) List<String> tags) {
        List<ResBookDto> response = this.bookService.filterBooksByTags(tags);
        
        if(tags != null){
            for(String tag:tags){
                System.out.println(tag);
            }
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResBookDto getBook(@PathVariable Integer id) {
        ResBookDto response = this.bookService.getBookById(id);

        return response;
    }
    
    //TODO: the rest of CRUD endpoints or admin
    
}
