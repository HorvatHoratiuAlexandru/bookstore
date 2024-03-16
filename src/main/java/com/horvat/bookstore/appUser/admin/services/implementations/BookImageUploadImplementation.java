package com.horvat.bookstore.appUser.admin.services.implementations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.horvat.bookstore.appUser.admin.services.BookImageUpload;
import com.horvat.bookstore.book.dtos.responses.ImageInfoDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class BookImageUploadImplementation implements BookImageUpload {
    public final String STATIC_IMAGE_PATH = "imageupload";

    @Override
    public List<ImageInfoDto> upload(MultipartFile[] files) {

        List<String> response = new LinkedList<>();
        for(MultipartFile file : files){

            String imageName = this.genRandomName() + ".jpg";
            response.add(imageName);

            File image = Paths.get(this.folderGetOrCreate().toString(), imageName).toFile();
            try(InputStream inputStream = file.getInputStream();
                OutputStream outputStream = new FileOutputStream(image))
            {
                byte[] buffer = new byte[1024];
                int byteReads;
                while((byteReads = inputStream.read(buffer)) != -1){
                    outputStream.write(buffer, 0, byteReads);
                }                
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        return ImageInfoDto.fromList(response);
    }

    private Path folderGetOrCreate(){
        Path filePath = FileSystems.getDefault().getPath(STATIC_IMAGE_PATH);

        try {
            if(!Files.exists(filePath)){
                Files.createDirectories(filePath);
            }    
            
            return filePath;

        } catch (Exception e) {
            log.error(e.getClass() + " : " + e.getMessage()); 
        }

        return null;
    }

    private String genRandomName(){
        Random rnd = new Random();
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', '*', '$', '@'};
        StringBuffer name = new StringBuffer();
        
        for(int i = 0; i < 24; i++){
            name.append(characters[rnd.nextInt(characters.length)]);
        }

        return name.toString();
    }

}
