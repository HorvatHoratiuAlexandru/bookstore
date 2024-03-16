package com.horvat.bookstore.configs.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.horvat.bookstore.appUser.admin.dtos.ReqCreateBookDto;
import com.horvat.bookstore.appUser.admin.services.AdminBookService;
import com.horvat.bookstore.appUser.admin.services.BookImageUpload;
import com.horvat.bookstore.book.BookModel;
import com.horvat.bookstore.book.BookRepository;
import com.horvat.bookstore.book.TagModel;
import com.horvat.bookstore.book.TagsRepository;
import com.horvat.bookstore.book.dtos.responses.ImageInfoDto;
import com.horvat.bookstore.utils.Utils;

@Component
@Order(2)
public class PopulateBooks implements CommandLineRunner {
    @Value("${seed.title-folder}")
    public String bookSampleFolder;
    @Value("${seed.author-folder}")
    public String bookAuthorFolder;
    @Value("${seed.seeding}")
    public Boolean seeding;
    @Autowired
    private Utils utils;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TagsRepository tagsRepository;


    @Autowired
    private AdminBookService adminBookService;
    @Autowired
    private BookImageUpload bookImageUploadService;


    @Override
    public void run(String... args) throws Exception {
        if(seeding == true){
            List<String> titles = utils.getTitlesFromFiles(this.bookSampleFolder);
            Map<String, List<String>> authors = utils.getAuthorsFromFiles(this.bookAuthorFolder);
            Random rnd = new Random();

            List<BookModel> queryResult = this.bookRepository.findAll();
            List<String> existingBooks =queryResult.stream().map(BookModel::getTitle).collect(Collectors.toList());
            titles.removeAll(existingBooks);

            for(String title: titles){
                ReqCreateBookDto bookDto = new ReqCreateBookDto();

                String[] image = {this.bookSampleFolder+"/"+title};
                List<ImageInfoDto> links = this.bookImageUploadService.upload(getMultipartFiles(image));
                List<String> links_string = links.stream()
                    .map(ImageInfoDto::getLink)
                    .collect(Collectors.toList());

                bookDto.setNewAuthors(authors.get(title));
                bookDto.setAuthors(new ArrayList<>());
                bookDto.setTags(this.getRandomTags(rnd));
                bookDto.setImages(links_string);

                bookDto.setTitle(title);
                bookDto.setStock(rnd.nextInt(10, 30));
                bookDto.setPrice(this.utils.getRandomBookPrice());
                bookDto.setDescription("This book is named: " + title + " and its amazing");
                bookDto.setPageNumber(rnd.nextInt(100, 500));



                adminBookService.create(bookDto);
            }
        }else{
            System.out.println("Skip Seeding");
        }

    
    }

    private MultipartFile[] getMultipartFiles(String[] images) throws IOException{
        String filePath = images[0];
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            MockMultipartFile[] response= {new MockMultipartFile(file.getName(), file.getName(), "image/jpeg", fileInputStream)};
            return response;
            
        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;
    }

    private List<Integer> getRandomTags(Random rnd){
        List<TagModel> queryResult = this.tagsRepository.findAll();
        List<Integer> response = new LinkedList<>();

        for(int i = 0; i<3; i++){
            response.add(queryResult.get(rnd.nextInt(queryResult.size())).getId());
        }

        return response;
    }

   


}
