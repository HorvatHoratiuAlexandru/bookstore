package com.horvat.bookstore.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    // return all books or all books based on tags
    @GetMapping
    public List<ResBookDto> filterBooks(@RequestParam(value = "tags", required = false) List<String> tags) {
        List<ResBookDto> response = this.bookService.filterBooksByTags(tags);

        return response;
    }

    @GetMapping("/{id}")
    public ResBookDto getBook(@PathVariable Integer id) {
        ResBookDto response = this.bookService.getBookById(id);

        return response;
    }
    
    @GetMapping("/search")
    public List<ResBookDto> getMethodName(@RequestParam(value = "search", required = true) String searchText) {
        log.info("Searching for book | search text:" + searchText);
        return this.bookService.searchBook(searchText);
    }
    
    
}
