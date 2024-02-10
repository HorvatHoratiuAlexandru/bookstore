package com.horvat.bookstore.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public List<String> getTitlesFromFiles(String path){
        List<String> bookTitles = new ArrayList<>();
        File folder = new File(path);

        if(!folder.isDirectory()){
            System.out.println("Specified path is not a directory");
            //throw an error
            return null;
        }

        File[] files = folder.listFiles();
        System.out.println(files);
        for(File file:files){
            if(file.isFile()){
                bookTitles.add(file.getName());
            }
        }


        return bookTitles;
    }
}
