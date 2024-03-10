package com.horvat.bookstore.appUser.admin.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResBookUploadedSuccesfullDto {

    private Boolean created;
    private String link;

    public static ResBookUploadedSuccesfullDto fromId(Integer bookId){
        ResBookUploadedSuccesfullDto response = new ResBookUploadedSuccesfullDto();

        response.setCreated(true);
        response.setLink("/book/"+bookId);

        return response;
    }
}
