package com.marvel.zuptest.controllers.response;

import java.util.ArrayList;
import java.util.List;

public class CreatorsResponse {
    private List<ItemCreatorsResponse> items = new ArrayList<>();

    public List<ItemCreatorsResponse> getItems() {
        return items;
    }
}
