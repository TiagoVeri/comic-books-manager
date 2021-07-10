package com.marvel.zuptest.controllers;

import com.marvel.zuptest.controllers.response.ComicsResponse;
import com.marvel.zuptest.services.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marvel")
public class ComicsController {

    @Autowired
    private ComicsService service;

    @GetMapping("/comics")
    public ComicsResponse findAll() {
        return service.findAll();
    }

    @GetMapping("/comics/{comicId}")
    public ComicsResponse findById(@PathVariable Integer comicId){
        return service.findbyId(comicId);
    }
}
