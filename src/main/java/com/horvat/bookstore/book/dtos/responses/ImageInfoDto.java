package com.horvat.bookstore.book.dtos.responses;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageInfoDto {
    private String link;


    public ImageInfoDto(String link){
        this.link = link;
    }

    public static List<ImageInfoDto> fromList(List<String> links){
        List<ImageInfoDto> response = new LinkedList<>();

        for(String link : links){
            response.add(new ImageInfoDto(link));
        } 

        return response;
    }
}
