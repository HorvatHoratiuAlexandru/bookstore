package com.horvat.bookstore.configs.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.horvat.bookstore.book.AuthorModel;
import com.horvat.bookstore.book.AuthorRepository;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.TagModel;
import com.horvat.bookstore.utils.Utils;

@Component
public class PopulateBooks implements CommandLineRunner {
    @Value("${seed.title-folder}")
    public String bookSampleFolder;
    @Value("${seed.author-folder}")
    public String bookAuthorFolder;
    @Value("${seed.seeding}")
    public Boolean seeding;
    @Autowired
    private Utils utils;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception { 
        if(seeding == true){
            List<String> titles = utils.getTitlesFromFiles(this.bookSampleFolder);
            Map<String, List<String>> authors = utils.getAuthorsFromFiles(this.bookAuthorFolder);

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

            for(String title: titles){
                List<BookModel> bookList = this.bookRepository.findByTitle(title);

                if(bookList.isEmpty()){
                    continue;
                }
                BookModel book = bookList.get(0);
                List<String> bookAuthors = authors.get(title);

                if(!(bookAuthors == null) && !(bookAuthors.isEmpty())){

                    for(String a:bookAuthors){
                        Optional<AuthorModel> existentAuthor = this.authorRepository.getByName(a);
                        if (book.getAuthors() == null) {
                            book.setAuthors(new HashSet<>());
                        }

                        if(existentAuthor.isPresent()){
                            book.getAuthors().add(existentAuthor.get());
                        }else{
                            AuthorModel newAuthor = new AuthorModel();
                            newAuthor.setName(a);
                            newAuthor = this.authorRepository.save(newAuthor);
                            book.getAuthors().add(newAuthor);
                        }
                    }
                }
            }
        }else{
            System.out.println("Skip Seeding");
        }
    }



}
