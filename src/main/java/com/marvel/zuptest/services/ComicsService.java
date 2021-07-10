package com.marvel.zuptest.services;

import com.marvel.zuptest.controllers.response.*;
import com.marvel.zuptest.exceptions.EntityNotFoundException;
import com.marvel.zuptest.models.Comics;
import com.marvel.zuptest.repositories.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ComicsService {

    @Autowired
    private ComicsMarvelService comicsMarvelService;

    @Autowired
    private ComicsRepository comicsRepository;

    public Comics addComic(Integer comicId){
        Comics comic = new Comics();
        //TODO not found exception
        ComicsResponse marvelComic = comicsMarvelService.findbyId(comicId);

        ResultsResponse dataResponse = marvelComic.getData().getResults().get(0);
        List<PricesResponse> prices = dataResponse.getPrices();
        Set<String> creators = new HashSet<>();
        comic.setComicId(dataResponse.getId());
        comic.setTitle(dataResponse.getTitle());

        for(PricesResponse price : prices){
           if(price.getType().equals("printPrice")) {
              BigDecimal value = new BigDecimal(price.getPrice());
              comic.setPrice(value);
           }
        }
        for(ItemCreatorsResponse creator : dataResponse.getCreators().getItems()){
           creators.add(creator.getName());
        }
        comic.setCreators(creators);
        comic.setIsbn(dataResponse.getIsbn());
        comic.setDescription(dataResponse.getDescription());

;       return comicsRepository.save(comic);
    }

    public Comics findComicById(Integer id){
        Optional<Comics> comic = comicsRepository.findById(id);
        return comic.orElseThrow(() ->  new EntityNotFoundException("Comic Book Not Found"));
    }
}
