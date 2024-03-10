package com.horvat.bookstore.appUser.admin.services;

import java.util.List;

import com.horvat.bookstore.appUser.admin.dtos.ReqCreateBookDto;
import com.horvat.bookstore.appUser.admin.dtos.ResBookUploadedSuccesfullDto;
import com.horvat.bookstore.appUser.admin.dtos.ResSearchResult;

// should return a link for the book, if the book is uploaded as id 1 then it should return {action: succesfull, link: dns/book/1}
public interface AdminBookService {
    ResBookUploadedSuccesfullDto create(ReqCreateBookDto bookDto);

    List<ResSearchResult> searchAuthor(String searchText);

    List<ResSearchResult> searchTag(String searchText);
}
