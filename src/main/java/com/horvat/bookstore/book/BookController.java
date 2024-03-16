package com.horvat.bookstore.book;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horvat.bookstore.book.dtos.requests.ReqReviewDto;
import com.horvat.bookstore.book.dtos.responses.ResBookDto;
import com.horvat.bookstore.book.dtos.responses.ResReviewDto;
import com.horvat.bookstore.configs.security.JwtAuthentication;

import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


@Log4j2
@RestController
@RequestMapping("/book")
public class BookController {
    public final String STATIC_IMAGE_PATH = "imageupload";
    @Autowired
    private BookService bookService;
    @Autowired
    private ReviewServiceImplementation reviewService;

    @GetMapping
    public List<ResBookDto> filterBooks(@RequestParam(value = "tags", required = false) List<String> tags) {
        log.info("GET:/book recived" + "\n" + tags);
        List<ResBookDto> response = this.bookService.filterBooksByTags(tags);
        log.info("GET:/book returned" + "\n" + response);
        return response;
    }

    @GetMapping("/{id}")
    public ResBookDto getBook(@PathVariable Integer id) {
        ResBookDto response = this.bookService.getBookById(id);
        log.info("GET:/book/" + id + "returned" + "\n" + response);
        return response;
    }
    
    @GetMapping("/search")
    public List<ResBookDto> searchBooks(@RequestParam(value = "search", required = true) String searchText) {
        log.info("Searching for book | search text:" + searchText);
        return this.bookService.searchBook(searchText);
    }

    @PostMapping("/{bookId}/review")
    public List<ResReviewDto> addReview(@RequestBody ReqReviewDto reviewDto, @PathVariable Integer bookId) {
        
        JwtAuthentication auth = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        List<ResReviewDto> response = this.reviewService.addReview(bookId, auth.getUserUID(), reviewDto);
        log.info("User: " + auth.getUserUID() + " added a review");
        
        return response;
    }

    @PutMapping("/{bookId}/review")
    public List<ResReviewDto> updateReview(@RequestBody ReqReviewDto reviewDto, @PathVariable Integer bookId) {
        
        JwtAuthentication auth = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        List<ResReviewDto> response = this.reviewService.update(bookId, auth.getUserUID(), reviewDto);
        log.info("User: " + auth.getUserUID() + " updated a review");
        
        return response;
    }

    @DeleteMapping("/{bookId}/review")
    public List<ResReviewDto> deleteReview(@PathVariable Integer bookId) {
        
        JwtAuthentication auth = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        List<ResReviewDto> response = this.reviewService.delete(bookId, auth.getUserUID());
        log.info("User: " + auth.getUserUID() + " updated a review");
        
        return response;
    }

    @GetMapping("/{bookId}/review")
    public List<ResReviewDto> getBookReviews(@PathVariable Integer bookId) {
        List<ResReviewDto> response = this.reviewService.getAll(bookId);
        
        return response;
    }
    
    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> downloadImages(@PathVariable String filename) {

        String currentDirectory = System.getProperty("user.dir");
        Path directory = Paths.get(currentDirectory);
        log.info("directory path: " + directory.toString());

        Path targetPath = directory.resolve(STATIC_IMAGE_PATH).resolve(filename);
        log.info("searching for file in: " + targetPath.toString());

        log.info("File exists: " + Files.exists(targetPath));
        log.info("File is directory: " + Files.isDirectory(targetPath));
        log.info("Trying to get: " + filename);
        if (targetPath==null || !Files.exists(targetPath)) {
            return ResponseEntity.notFound().build();
        }

        
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new PathResource(targetPath));
    }
    
}
