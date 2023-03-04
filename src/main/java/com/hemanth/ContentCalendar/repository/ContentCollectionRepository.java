package com.hemanth.ContentCalendar.repository;

import com.hemanth.ContentCalendar.entity.Content;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
