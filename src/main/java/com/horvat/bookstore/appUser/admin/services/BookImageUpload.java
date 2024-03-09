package com.horvat.bookstore.appUser.admin.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.horvat.bookstore.book.dtos.responses.ImageInfoDto;

public interface BookImageUpload {

    public List<ImageInfoDto> upload(MultipartFile[] files);
}
