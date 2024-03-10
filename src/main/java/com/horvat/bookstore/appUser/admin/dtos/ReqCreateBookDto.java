package com.horvat.bookstore.appUser.admin.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReqCreateBookDto {
    private String title;
    
    private String description;
    
    private Integer pageNumber;
    
    private Float price;
  
    private Integer stock;

    private List<String> newAuthors;

    private List<Integer> tags;

    private List<Integer> authors;

    private List<String> images;
}
