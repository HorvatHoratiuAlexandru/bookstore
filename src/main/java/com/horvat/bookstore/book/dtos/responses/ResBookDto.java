package com.horvat.bookstore.book.dtos.responses;

import java.util.List;

import com.horvat.bookstore.book.TagModel;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResBookDto {
    private Integer id;

    private String title;
    private String description;
    private Integer pageNumber;
    private Float price;
    private Integer stock;

    private List<TagModel> tags;
}
