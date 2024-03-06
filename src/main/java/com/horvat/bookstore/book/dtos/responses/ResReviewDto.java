package com.horvat.bookstore.book.dtos.responses;

import java.util.LinkedList;
import java.util.List;

import com.horvat.bookstore.book.ReviewModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResReviewDto {
    private Integer id;
    private String grade;
    private String text;
    private String ownerName;
    
    public static ResReviewDto fromEntiry(ReviewModel review){
        ResReviewDto response = new ResReviewDto();

        response.setId(review.getId());
        response.setGrade(review.getGrade().toString());
        response.setText(review.getText());
        response.setOwnerName(review.getUser().getFullName());

        return response;
    }

    public static List<ResReviewDto> fromEntiryList(List<ReviewModel> reviews){
        List<ResReviewDto> response = new LinkedList<>();

        for(ReviewModel review: reviews){
            response.add(fromEntiry(review));
        }

        return response;
    }
}
