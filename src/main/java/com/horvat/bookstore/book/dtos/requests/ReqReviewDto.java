package com.horvat.bookstore.book.dtos.requests;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.ReviewGrade;
import com.horvat.bookstore.book.ReviewModel;
import com.horvat.bookstore.book.exceptions.BadReviewGrade;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReqReviewDto {
    @NotBlank
    private String grade;
    @NotBlank
    private String text;

    public ReviewModel toEntity(BookModel book, UserModel user){
        ReviewModel review = new ReviewModel();
        ReviewGrade gradeNum;
        try {
            gradeNum = ReviewGrade.valueOf(grade.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadReviewGrade("Bad Revie Grade With Value:" + grade);
        }

        review.setBook(book);
        review.setUser(user);
        review.setGrade(gradeNum);
        review.setText(text);

        return review;
    }
}
