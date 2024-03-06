package com.horvat.bookstore.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.horvat.bookstore.appUser.UserModel;
import com.horvat.bookstore.appUser.UserRepository;
import com.horvat.bookstore.appUser.exceptions.UserNotFoundException;
import com.horvat.bookstore.book.dtos.requests.ReqReviewDto;
import com.horvat.bookstore.book.dtos.responses.ResReviewDto;
import com.horvat.bookstore.book.exceptions.AlreadyReviewedException;
import com.horvat.bookstore.book.exceptions.BookNotFoundException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ReviewServiceImplementation {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReviewRepository reviewRepository;


    public List<ResReviewDto> getAll(Integer bookId){
        BookModel book = this.getBook(bookId);
        List<ReviewModel> bookReviews = this.reviewRepository.findByBook(book);
    
        return ResReviewDto.fromEntiryList(bookReviews);
    }

    @Transactional
    public List<ResReviewDto> addReview(Integer bookId, String userUid, ReqReviewDto reviewDto){
        BookModel book = this.getBook(bookId);
        UserModel user = this.getUser(userUid);

        if(this.userReviewedAlready(user, bookId)){
            String message = "You already have a review for book " + book.getTitle() + " try updating it";
            log.error(message);
            throw new AlreadyReviewedException(message);
        }

        ReviewModel review = reviewDto.toEntity(book, user);
        
        this.reviewRepository.save(review);

        List<ReviewModel> bookReviews = this.calculateBookGrade(book);

    
        return ResReviewDto.fromEntiryList(bookReviews);
    }

    @Transactional
    public List<ResReviewDto> update(Integer bookId, String userUid, ReqReviewDto reviewDto){
        BookModel book = this.getBook(bookId);
        UserModel user = this.getUser(userUid);

        if(!this.userReviewedAlready(user, bookId)){
            this.addReview(bookId, userUid, reviewDto);
        }

        List<ReviewModel> reviewQueryResult = this.reviewRepository.findByUser(user);
        ReviewModel toUpdate = reviewDto.toEntity(book, user);

        for(ReviewModel review : reviewQueryResult){
            if(review.getUser().getUid().equals(user.getUid())
            && review.getBook().getId() == bookId){
                toUpdate.setId(review.getId());;
                break;
            }
        }

        this.reviewRepository.save(toUpdate);

        List<ReviewModel> bookReviews = this.calculateBookGrade(book);

        return ResReviewDto.fromEntiryList(bookReviews);
        
    }

    @Transactional
    public List<ResReviewDto> delete(Integer bookId, String userUid){
        BookModel book = this.getBook(bookId);
        UserModel user = this.getUser(userUid);


        List<ReviewModel> reviewQueryResult = this.reviewRepository.findByUser(user);
        for(ReviewModel review : reviewQueryResult){
            if(review.getUser().getUid().equals(user.getUid())
            && review.getBook().getId() == bookId){
                this.reviewRepository.deleteById(review.getId());
                break;
            }
        }

        List<ReviewModel> bookReviews = this.calculateBookGrade(book);

        return ResReviewDto.fromEntiryList(bookReviews);
    }

    private List<ReviewModel> calculateBookGrade(BookModel book){
        List<ReviewModel> bookReviews = this.reviewRepository.findByBook(book);
        
        book.setGrade(this.valueToReviewGrade(this.bookReviewScore(bookReviews)));
        this.bookRepository.save(book);
        
        return bookReviews;
    }


    private boolean userReviewedAlready(UserModel user, Integer bookId){
        List<ReviewModel> reviewQueryResult = this.reviewRepository.findByUser(user);

        if(reviewQueryResult == null || reviewQueryResult.isEmpty()){
            return false;
        }

        for(ReviewModel review : reviewQueryResult){
            if(review.getUser().getUid().equals(user.getUid())
            && review.getBook().getId() == bookId){
                return true;
            }
        }

        return false;
    }

    private UserModel getUser(String userUid){
        List<UserModel> userQueryResult = this.userRepository.findByUid(userUid);

        if(userQueryResult == null || userQueryResult.isEmpty()){
            String message = "User with id: " + userUid + " Not Found";
            log.error(message);
            throw new UserNotFoundException(message);
        }

        return userQueryResult.get(0);
    }

    private BookModel getBook(Integer bookId){
        Optional<BookModel> bookOptional = this.bookRepository.findById(bookId);
        if(!bookOptional.isPresent()){
            String message = "Book with id: " + bookId + " Not Found";
            log.error(message);
            throw new BookNotFoundException(message);
        }

        return bookOptional.get();
    }

    private Float bookReviewScore(List<ReviewModel> reviews){
        Float total = 0.0f;
        Integer reviewsNum = reviews.size();

        for(ReviewModel review : reviews){
            total += this.reviewEnumToValue(review.getGrade());
        }

        return total/reviewsNum;
    }

    private Integer reviewEnumToValue(ReviewGrade grade){
        Integer score = 0;
        switch (grade) {
            case POOR:
                score += 1;
                break;
            case FAIR:
                score += 2;
                break;
            case GOOD:
                score += 3;
                break;
            case VERY_GOOD:
                score += 4;
                break;
            case EXCELLENT:
                score += 5;
                break;
            default:
                //TODO: Throw an exception
                log.error("Unknown grade provided: " + grade);
        }

        return score;
    }

    private ReviewGrade valueToReviewGrade(Float value){
        ReviewGrade grade;
       
        if(value < 1.5){
            grade = ReviewGrade.POOR;
        }else if(value < 2.5){
            grade = ReviewGrade.FAIR;
        }else if(value < 3.5){
            grade = ReviewGrade.GOOD;
        }else if(value < 4.5){
            grade = ReviewGrade.VERY_GOOD;
        }else{
            grade = ReviewGrade.EXCELLENT;
        }
        
        if(value > 5){
            //TODO: Throw an exception
            grade = ReviewGrade.EXCELLENT;
            log.error("Unknown value or grade provided: " + value);        
        }

        return grade;
    }
}
