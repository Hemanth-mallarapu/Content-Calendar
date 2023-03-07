package com.hemanth.ContentCalendar.controller;

import com.hemanth.ContentCalendar.entity.Content;
import com.hemanth.ContentCalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/content")
public class ContentController {

    private final ContentCollectionRepository repository;


    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "No Content Found"));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@Valid @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id){

        if(!repository.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Content Found");
        }
        repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        if(!repository.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found with this "+ id);
        }
        repository.delete(id);
    }
}
