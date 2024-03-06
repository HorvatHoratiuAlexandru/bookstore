package com.horvat.bookstore.book;

import org.springframework.data.repository.Repository;
import java.util.List;
import com.horvat.bookstore.appUser.UserModel;



public interface ReviewRepository extends Repository<ReviewModel,Integer> {
    ReviewModel save(ReviewModel review);
    void deleteById(Integer id);
    List<ReviewModel> findByBook(BookModel book);
    List<ReviewModel> findByUser(UserModel user);
}
