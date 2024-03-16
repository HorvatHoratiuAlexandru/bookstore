package com.horvat.bookstore.configs.commands;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.horvat.bookstore.book.Tag;
import com.horvat.bookstore.book.TagModel;
import com.horvat.bookstore.book.TagsRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Order(1)
@Log4j2
public class AddTags implements CommandLineRunner {
    @Autowired
    TagsRepository tagsRepository;

    @Override
    public void run(String... args) throws Exception {
        List<TagModel> tagsToAdd = new LinkedList<>();
        
        for(Tag t: Tag.values()){
            log.info("adding TAG: " + t.name());
            Optional<TagModel> tagOptional = this.tagsRepository.findByName(t.name());
            if(!tagOptional.isPresent()){
                TagModel newTag = new TagModel();
                newTag.setName(t.name());
                tagsToAdd.add(newTag);
            }
        }
        if(!tagsToAdd.isEmpty()){
            this.tagsRepository.saveAll(tagsToAdd);
        }
    }

}
