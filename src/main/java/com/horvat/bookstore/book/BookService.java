package com.horvat.bookstore.book;

import java.util.List;

import com.horvat.bookstore.book.dtos.responses.ResBookDto;

public interface BookService {

    public ResBookDto getBookById(Integer id);

    public List<ResBookDto> filterBooksByTags(List<String> tags);

    public List<ResBookDto> searchBook(String searchString);

}
