package com.horvat.bookstore.book.dtos.responses;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.horvat.bookstore.book.AuthorModel;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.ImageModel;
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
    private String grade;

    private List<String> authors;
    private List<String> tags;
    private List<String> images;

    
    public static ResBookDto fromEntity(BookModel book){
        ResBookDto response = new ResBookDto();
        if(book==null) return response;

        response.setAuthors(new LinkedList<>());
        response.setTags(new LinkedList<>());
        response.setImages(new LinkedList<>());    
        BeanUtils.copyProperties(book, response);
        for(AuthorModel a : book.getAuthors()){
            response.getAuthors().add(a.getName());
        }
        for(TagModel t : book.getTags()){
            response.getTags().add(t.getName());
        }
        if(book.getGrade() != null){
            response.setGrade(book.getGrade().toString());
        }else{
            response.setGrade("NA");
        }

        for(ImageModel i : book.getImages()){
            response.getImages().add(i.getLink());
        }

        return response;
    }

    public static List<ResBookDto> fromIterableEntity(List<BookModel> books){
        List<ResBookDto> response = new LinkedList<>();

        if(books.isEmpty()){
            return null;
        }

        for(BookModel b: books){
            response.add(ResBookDto.fromEntity(b));
        }

        return response;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("BookDto:").append("\n")
        .append("id:").append(this.id).append("\n")
        .append("title:").append(this.title).append("\n");
        return sb.toString();
    }
}
