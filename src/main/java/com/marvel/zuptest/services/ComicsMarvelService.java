package com.marvel.zuptest.services;

import com.marvel.zuptest.clients.MarvelComicsClient;
import com.marvel.zuptest.controllers.response.ComicsMarvelResponse;
import com.marvel.zuptest.exceptions.EntityNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComicsMarvelService {
    private static final String PUBLIC_KEY = "Key bliped"; //Please add your own key generated in https://developer.marvel.com/
    private static final String PRIVATE_KEY = "Key bliped";
    Long timeStamp = new Date().getTime();

    @Autowired
    private MarvelComicsClient client;

    public ComicsMarvelResponse findAll() {
        return client.getAll(timeStamp, PUBLIC_KEY, buildHash(timeStamp));
    }

    public ComicsMarvelResponse findbyId(Integer id){
        return client.getById(id, timeStamp, PUBLIC_KEY, buildHash(timeStamp));
    }


    private String buildHash(Long timeStamp) {
        return DigestUtils.md5Hex(timeStamp + PRIVATE_KEY + PUBLIC_KEY);
    }

    public ComicsMarvelService (MarvelComicsClient client) {
        this.client = client;
    }
}
