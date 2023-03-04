package com.hemanth.ContentCalendar.repository;

import com.hemanth.ContentCalendar.entity.Content;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.hemanth.ContentCalendar.entity.Status.IDEA;
import static com.hemanth.ContentCalendar.entity.Type.ARTICLE;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){

        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }
    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existById(Integer id) {
        return contentList.stream().filter(content -> content.id().equals(id)).count() == 1;

    }

    @PostConstruct
    private void init(){
        Content c = new Content(1,
                "My first testing",
                "My first testing.",
                IDEA,
                ARTICLE,
                LocalDateTime.now(),
                null, "");
        contentList.add(c);
    }



}
