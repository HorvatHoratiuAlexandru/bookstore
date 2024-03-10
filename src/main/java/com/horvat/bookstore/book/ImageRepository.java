package com.horvat.bookstore.book;

import org.springframework.data.repository.Repository;

public interface ImageRepository extends Repository<ImageModel, Integer> {
    ImageModel save(ImageModel image);
}
