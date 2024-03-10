package com.horvat.bookstore.appUser.admin.services.implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horvat.bookstore.appUser.admin.dtos.ReqCreateBookDto;
import com.horvat.bookstore.appUser.admin.dtos.ResBookUploadedSuccesfullDto;
import com.horvat.bookstore.appUser.admin.dtos.ResSearchResult;
import com.horvat.bookstore.appUser.admin.services.AdminBookService;
import com.horvat.bookstore.book.AuthorModel;
import com.horvat.bookstore.book.AuthorRepository;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.ImageModel;
import com.horvat.bookstore.book.ImageRepository;
import com.horvat.bookstore.book.TagModel;
import com.horvat.bookstore.book.TagsRepository;
import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.exceptions.BookNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AdminBookServiceImplementation implements AdminBookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ResBookUploadedSuccesfullDto create(ReqCreateBookDto bookDto) {
        Set<TagModel> existingTags = this.getTags(bookDto.getTags());
        Set<AuthorModel> existingAuthors = this.getAuthors(bookDto.getAuthors());
        Set<AuthorModel> newAuthors = this.getNewAuthors(bookDto.getNewAuthors());

        BookModel newBook = new BookModel();
        newBook.setTags(new HashSet<>());
        newBook.setAuthors(new HashSet<>());
        newBook.setImages(new HashSet<>());
        newBook.setTitle(bookDto.getTitle());
        newBook.setDescription(bookDto.getDescription());
        newBook.setPageNumber(bookDto.getPageNumber());
        newBook.setStock(bookDto.getStock());
        newBook.setPrice(bookDto.getPrice());

        for(TagModel t : existingTags){
            newBook.getTags().add(t);
        }

        for(AuthorModel a : existingAuthors){
            a.getBooks().add(newBook);
            newBook.getAuthors().add(a);
        }

        for(AuthorModel a : newAuthors){
            a.setBooks(new HashSet<>());
            a.getBooks().add(newBook);
            newBook.getAuthors().add(a);
        }

        for(String link : bookDto.getImages()){
            ImageModel newImage = new ImageModel();
            newImage.setLink(link);

            newBook.getImages().add(this.imageRepository.save(newImage));
        }

        newBook = this.bookRepository.save(newBook);


        return ResBookUploadedSuccesfullDto.fromId(newBook.getId());
    }

    @Override
    public List<ResSearchResult> searchAuthor(String searchText) {
        log.info("Searching for authors like:" + searchText);
        List<AuthorModel> queryResult = this.authorRepository.search(searchText);
        log.info("Found: " + queryResult.size() + "authors");

        return ResSearchResult.fromAuthorList(queryResult);
    }

    @Override
    public List<ResSearchResult> searchTag(String searchText) {
        log.info("Searching for tags like:" + searchText);
        List<TagModel> queryResult = this.tagsRepository.search(searchText);
        log.info("Found: " + queryResult.size() + "tags");

        return ResSearchResult.fromTagList(queryResult);
    }

    public ResBookDto update(Integer bookId, Integer stock, Float price){
        Optional<BookModel> entity = this.bookRepository.findById(bookId);

        if(!entity.isPresent()){
            String message = "Book with id: " + bookId + " Not Found";
            log.error(message);
            throw new BookNotFoundException(message);
        }

        if(stock != null){
            entity.get().setStock(stock);
        }
        if(price != null){
            entity.get().setPrice(price);   
        }

        return ResBookDto.fromEntity(this.bookRepository.save(entity.get()));
    }

    private Set<AuthorModel> getAuthors(List<Integer> ids){
        Set<AuthorModel> response = new HashSet<>();

        for(Integer id : ids){
            Optional<AuthorModel> authorOptional = this.authorRepository.findById(id);
            if(!authorOptional.isPresent()){
                String message = "AuthorNotFound with id:" + id;
                log.error(message);
                throw new BookNotFoundException(message);
            }
            response.add(authorOptional.get());
        }

        return response;
    }

    private Set<TagModel> getTags(List<Integer> ids){
        Set<TagModel> response = new HashSet<>();

        for(Integer id : ids){
            Optional<TagModel> tagOptional = this.tagsRepository.findById(id);
            if(!tagOptional.isPresent()){
                String message = "AuthorNotFound with id:" + id;
                log.error(message);
                throw new BookNotFoundException(message);
            }
            response.add(tagOptional.get());
        }

        return response;
    }

    private Set<AuthorModel> getNewAuthors(List<String> names){
        Set<AuthorModel> newAuthors = new HashSet<>();

        for(String name: names){
            AuthorModel newAuthor = new AuthorModel();
            newAuthor.setName(name);
            newAuthors.add(this.authorRepository.save(newAuthor));
        }

        return newAuthors;
    }

}
