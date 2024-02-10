package com.horvat.bookstore.configs.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.TagModel;
import com.horvat.bookstore.utils.Utils;

@Component
public class PopulateBooks implements CommandLineRunner {
    @Value("${seed.title-folder}")
    public String bookSampleFolder;
    @Autowired
    private Utils utils;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception { 
        List<String> titles = utils.getTitlesFromFiles(this.bookSampleFolder);

        List<BookModel> books = new ArrayList<>();
        Set<TagModel> tags = new HashSet<>();
        tags.add(TagModel.ADVENTURE);
        tags.add(TagModel.CLASSICS);

        for(String title: titles){
            BookModel book = new BookModel();


            book.setTitle(title);
            book.setDescription("lorem ipsum abc");
            book.setPageNumber(100);
            book.setTags(tags);

            books.add(book);
        }

        this.bookRepository.saveAll(books);
    }

}
