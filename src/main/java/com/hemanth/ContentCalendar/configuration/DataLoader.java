package com.hemanth.ContentCalendar.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hemanth.ContentCalendar.entity.Content;
import com.hemanth.ContentCalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {


    private final ContentRepository repository;
    private final ObjectMapper mapper;

    public DataLoader(ContentRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("Hello");*/
        if(repository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")){
                repository.saveAll(mapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
            }
        }
    }
}
