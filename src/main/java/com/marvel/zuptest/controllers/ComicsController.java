package com.marvel.zuptest.controllers;

import com.marvel.zuptest.clients.MarvelComicsClient;
import com.marvel.zuptest.controllers.response.ComicsMarvelResponse;
import com.marvel.zuptest.models.Comics;
import com.marvel.zuptest.services.ComicsMarvelService;
import com.marvel.zuptest.services.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/marvel")
public class ComicsController {

    @Autowired
    private ComicsService service;

    @Autowired
    private ComicsMarvelService comicsMarvelService;

    //Get all Marvel comics from Marvel API
    @GetMapping("/comics/marvelapi")
    public ComicsMarvelResponse findAllMarvelComics() {
        return comicsMarvelService.findAll();
    }

    @GetMapping("/comics/{comicId}")
    public ResponseEntity<Comics> findById(@PathVariable Integer comicId){
        return ResponseEntity.ok(service.findComicById(comicId));
    }

    @PostMapping("/add")
    public ResponseEntity<Comics> addComicFromMarvel(@RequestParam Integer comicMarvelId){
        Comics comic = service.addComic(comicMarvelId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(comic.getComicId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
