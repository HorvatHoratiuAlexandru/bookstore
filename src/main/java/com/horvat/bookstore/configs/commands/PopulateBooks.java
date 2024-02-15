package com.horvat.bookstore.configs.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.horvat.bookstore.book.AuthorModel;
import com.horvat.bookstore.book.AuthorRepository;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.Tag;
import com.horvat.bookstore.book.TagModel;
import com.horvat.bookstore.book.TagsRepository;
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
    @Autowired
    private TagsRepository tagsRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception { 
        if(seeding == true){
            List<String> titles = utils.getTitlesFromFiles(this.bookSampleFolder);
            Map<String, List<String>> authors = utils.getAuthorsFromFiles(this.bookAuthorFolder);

            List<BookModel> books = new ArrayList<>();
            
            for(String title: titles){
                Set<TagModel> tags = new HashSet<>();

                Random random = new Random();
                Integer iterNum = random.nextInt(4) + 5;
                for(int i = 0; i<iterNum.intValue(); i++){
                    int rv = random.nextInt(22);
                    Tag[] allTags = Tag.values();

                    Optional<TagModel> tagExists = this.tagsRepository.findByName(allTags[rv].toString());
                    if(!tagExists.isPresent()){
                        TagModel randomTag = new TagModel();
                        randomTag.setName(allTags[rv].toString());
                        tags.add(randomTag);
                    }else{
                        tags.add(tagExists.get());
                    }
                    
                }
                this.tagsRepository.saveAll(tags);
                
                BookModel book = new BookModel();
                book.setTitle(title);
                book.setDescription("lorem ipsum abc");
                book.setPageNumber(100);
                book.setTags(tags);
                book.setPrice(utils.getRandomBookPrice());
                book.setStock(random.nextInt(10));


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
                            existentAuthor.get().getBooks().add(book);
                            this.authorRepository.save(existentAuthor.get());
                        }else{
                            AuthorModel newAuthor = new AuthorModel();
                            newAuthor.setBooks(new HashSet<>());
                            book.getAuthors().add(newAuthor);
                            newAuthor.getBooks().add(book);

                            newAuthor.setName(a);
                            this.authorRepository.save(newAuthor);
                            
                        }
                    }
                }
            }
        }else{
            System.out.println("Skip Seeding");
        }
    }

}
