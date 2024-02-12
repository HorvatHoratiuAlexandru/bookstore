package com.horvat.bookstore.utils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public Map<String, List<String>> getAuthorsFromFiles(String path){
        Map<String, List<String>> authors = new HashMap<>();
        
        File folder = new File(path);
        if(!folder.isDirectory()){
            System.out.println("Specified path is not a directory");
            //throw an error
            return null;
        }
        
        File[] files = folder.listFiles();
        for(File file:files){
            List<String> bookAuthors = new LinkedList<>();
            
            try{
                Scanner fileReader = new Scanner(file);
                if(fileReader.hasNext()){
                    String line = fileReader.nextLine();
                    String[] splitedLine = line.split("\\$");
                    
                    String title = splitedLine[0];
                    
                    String[] authorsArray = splitedLine[1].split(",");
                    for(String authorName: authorsArray){
                        bookAuthors.add(authorName);
                    }

                    authors.put(title, bookAuthors);
                }

                
            } catch (Exception e){
                System.out.println("from file Utils");
                System.out.println(e.toString());
            }
            
        }

        return authors;
    }
}
