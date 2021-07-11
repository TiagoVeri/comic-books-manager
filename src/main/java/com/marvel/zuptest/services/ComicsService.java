package com.marvel.zuptest.services;

import com.marvel.zuptest.controllers.response.*;
import com.marvel.zuptest.exceptions.EntityNotFoundException;
import com.marvel.zuptest.models.Comics;
import com.marvel.zuptest.repositories.ComicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class ComicsService {

    @Autowired
    private ComicsMarvelService comicsMarvelService;

    @Autowired
    private ComicsRepository comicsRepository;

    public Comics addComic(Integer comicId) throws EntityNotFoundException{
        Comics comic = new Comics();
        ComicsMarvelResponse marvelComic = comicsMarvelService.findbyId(comicId);

        if(marvelComic.getData() == null){
            throw new EntityNotFoundException("Comic book not found in Marvel API");
        }
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

        if(dataResponse.getIsbn().length() > 0){
            char isbn =  dataResponse.getIsbn().charAt(dataResponse.getIsbn().length() - 1);
            DayOfWeek isbnDay = verifyIbnsDay(isbn);
            comic.setIsbnDay(isbnDay);
            comic.setIsbn(dataResponse.getIsbn());
        } else {
            comic.setIsbn("No ISBN Registered in Marvel API");
        }

       return comicsRepository.save(comic);
    }

    public Comics findComicById(Integer id){
        Optional<Comics> comic = comicsRepository.findById(id);
        return comic.orElseThrow(() ->  new EntityNotFoundException("Comic Book Not Found"));
    }

    public DayOfWeek verifyIbnsDay(char value){

        int lastIbns = Integer.parseInt(String.valueOf(value));

        if(lastIbns == 0 || lastIbns == 1 ){
            return DayOfWeek.MONDAY;
        }
        if(lastIbns == 2 || lastIbns == 3 ){
            return DayOfWeek.TUESDAY;
        }
        if(lastIbns == 4 || lastIbns == 5 ){
            return DayOfWeek.WEDNESDAY;
        }
        if(lastIbns == 6 || lastIbns == 7 ){
            return DayOfWeek.THURSDAY;
        }
        if(lastIbns == 8 || lastIbns == 9 ){
            return DayOfWeek.FRIDAY;
        }
        return null;
    }
}
