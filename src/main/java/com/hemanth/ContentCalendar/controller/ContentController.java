package com.hemanth.ContentCalendar.controller;

import com.hemanth.ContentCalendar.entity.Content;
import com.hemanth.ContentCalendar.entity.Status;
import com.hemanth.ContentCalendar.repository.ContentCollectionRepository;
import com.hemanth.ContentCalendar.repository.ContentJdbcTemplateRepository;
import com.hemanth.ContentCalendar.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/content")
public class ContentController {

    //private final ContentCollectionRepository repository;
    //@Autowired
    //private final ContentJdbcTemplateRepository repository;

    private final ContentRepository repository;

    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }
/*
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }
*/

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

        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Content Found");
        }
        repository.save(content);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        /*if(!repository.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found with this "+ id);
        }*/
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No records found with this "+ id);
        }
        repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){

        return repository.findAllByTitleContains(keyword);

    }
    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status){
        return repository.listByStatus(status);
    }
}
