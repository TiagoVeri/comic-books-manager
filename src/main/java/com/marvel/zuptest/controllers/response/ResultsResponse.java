package com.marvel.zuptest.controllers.response;

import java.util.ArrayList;
import java.util.List;

public class ResultsResponse {
    private Integer id;
    private String title;

    List<PricesResponse> prices = new ArrayList<>();
    CreatorsResponse creators;

    private String isbn;
    private String description;


    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<PricesResponse> getPrices() {
        return prices;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public CreatorsResponse getCreators() {
        return creators;
    }
}
