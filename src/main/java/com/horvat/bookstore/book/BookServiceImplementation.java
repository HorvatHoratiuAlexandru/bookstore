package com.horvat.bookstore.book;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;

@Service
public class BookServiceImplementation implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagsRepository tagsRepository;

    @Override
    public ResBookDto getBookById(Integer id) {
        Optional<BookModel> bookOptional = this.bookRepository.findById(id); 
        
        if(bookOptional.isPresent()) return ResBookDto.fromEntity(bookOptional.get());

        // TODO: Throw an not found exception
        return null;        
    }

    @Override
    public List<ResBookDto> filterBooksByTags(List<String> tags) {
        if(tags == null){
            return getAllBooks();
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
        // TODO Find a book that has search string in title or description?
        throw new UnsupportedOperationException("Unimplemented method 'searchBook'");
    }

    private List<ResBookDto> getAllBooks() {
        List<ResBookDto> response = new LinkedList<>();
        List<BookModel> books = this.bookRepository.findAll();

        for(BookModel book: books){
            response.add(ResBookDto.fromEntity(book));
        }

        return response;
    }

}
