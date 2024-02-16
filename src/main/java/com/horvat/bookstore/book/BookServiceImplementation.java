package com.horvat.bookstore.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.exceptions.BookNotFoundException;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public ResBookDto getBookById(Integer id) {
        return ResBookDto.fromEntity(this.findBook(id));
    }

    @Override
    public List<ResBookDto> filterBooksByTags(List<String> tags) {
        if(tags == null){
            return ResBookDto.fromIterableEntity(this.getAllBooks());
        }

        Set<Tag> tagList = EnumSet.noneOf(Tag.class);
        Set<TagModel> tagModelList = new HashSet<>();

        for(String tag: tags){
            tagList.add(Tag.valueOf(tag.toUpperCase()));
        }
        for(Tag tag: tagList){
            Optional<TagModel> dbTag = this.tagsRepository.findByName(tag.name());
            if(dbTag.isPresent()){
                tagModelList.add(dbTag.get());
            }
        }
        List<BookModel> bookList = this.bookRepository.findByTagsIn(tagModelList);
        
        return ResBookDto.fromIterableEntity(bookList); 
        
    }

    @Override
    public List<ResBookDto> searchBook(String searchString) {
        String[] tokens = searchString.split(" ");
        Set<BookModel> matchedBooks = new HashSet<>();

        for(String t: tokens){
            matchedBooks.addAll(findByToken(t));
        }

        return ResBookDto.fromIterableEntity(new ArrayList<>(matchedBooks));
    }

    private List<BookModel> getAllBooks() {
        List<BookModel> books = this.bookRepository.findAll();

        return books;
    }

    private BookModel findBook(Integer id){
        Optional<BookModel> bookOptional = this.bookRepository.findById(id); 
        
        if(bookOptional.isPresent()) return bookOptional.get();

        StringBuilder sb = new StringBuilder();
        sb.append("Book with Id: ").append(id.toString()).append(" NotFound");
        throw new BookNotFoundException(sb.toString());
    }

    private List<BookModel> findByToken(String token){
        List<BookModel> books = this.bookRepository.searchBooks(token);
        return books != null ? books : Collections.emptyList();
    }

}
