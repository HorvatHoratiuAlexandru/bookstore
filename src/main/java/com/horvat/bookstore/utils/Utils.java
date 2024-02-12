package com.horvat.bookstore.utils;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    public List<String> getTitlesFromFiles(String path){
        FileUtils util = new FileUtils();

        return util.getTitlesFromFiles(path);
    }

     public Map<String, List<String>> getAuthorsFromFiles(String path){
        FileUtils util = new FileUtils();

        return util.getAuthorsFromFiles(path);
     }
}
