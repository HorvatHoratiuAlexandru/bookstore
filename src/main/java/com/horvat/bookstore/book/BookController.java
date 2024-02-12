package com.horvat.bookstore.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/book")
public class BookController {

    // return all books or all books based on filter
    @GetMapping
    public List<ResBookDto> filterBooks(@RequestParam(value = "filter", required = false) List<String> filters) {
        List<ResBookDto> response = new ArrayList<>();
        
        if(filters != null){
            for(String filter:filters){
                System.out.println(filter);
            }
        }

        return response;
    }

    @GetMapping("/{id}")
    public ResBookDto getBook(@PathVariable Integer id) {
        ResBookDto response = new ResBookDto();

        return response;
    }
    
    //TODO: the rest of CRUD endpoints or admin
    
}
