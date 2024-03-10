package com.horvat.bookstore.appUser.admin.dtos;

import java.util.LinkedList;
import java.util.List;

import com.horvat.bookstore.book.AuthorModel;
import com.horvat.bookstore.book.TagModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResSearchResult {
    private Integer id;
    private String suggestion;

    public ResSearchResult(Integer id, String suggestion){
        this.id = id;
        this.suggestion = suggestion;
    }


    public static List<ResSearchResult> fromAuthorList(List<AuthorModel> entities){
        List<ResSearchResult> response = new LinkedList<>();

        for(AuthorModel e:entities){
            response.add(new ResSearchResult(e.getId(), e.getName()));
        }

        return response;
    }

    public static List<ResSearchResult> fromTagList(List<TagModel> entities){
        List<ResSearchResult> response = new LinkedList<>();
        
        for(TagModel e:entities){
            response.add(new ResSearchResult(e.getId(), e.getName()));
        }

        return response;
    }
}
