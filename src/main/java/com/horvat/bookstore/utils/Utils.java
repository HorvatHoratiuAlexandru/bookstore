package com.horvat.bookstore.utils;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Utils {
    public List<String> getTitlesFromFiles(String path){
        FileUtils util = new FileUtils();

        return util.getTitlesFromFiles(path);
    }
}
