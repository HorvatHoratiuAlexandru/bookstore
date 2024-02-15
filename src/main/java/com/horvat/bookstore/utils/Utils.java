package com.horvat.bookstore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

     public Float getRandomBookPrice(){
        List<Integer> dividers = new ArrayList<Integer>(Arrays.asList(1,2,4));
        Random rnd = new Random();
        float integral = (float) rnd.nextInt(100);
        float fractional = (float) 1/dividers.get(rnd.nextInt(3));

        return integral+fractional;
     }
}
