package com.marvel.zuptest.services;

import com.marvel.zuptest.clients.MarvelComicsClient;
import com.marvel.zuptest.controllers.response.ComicsResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ComicsMarvelService {
    private static final String PUBLIC_KEY = "7b953bcac875acc429e157c128a65229";
    private static final String PRIVATE_KEY = "4bb711c034b8901ad412cd2cee8f15e55c4e59b4";
    Long timeStamp = new Date().getTime();

    @Autowired
    private MarvelComicsClient client;

    public ComicsResponse findAll() {
     return client.getAll(timeStamp, PUBLIC_KEY, buildHash(timeStamp));
    }

    public ComicsResponse findbyId(Integer id) {
        return client.getById(id, timeStamp, PUBLIC_KEY, buildHash(timeStamp));
    }


    private String buildHash(Long timeStamp) {
        return DigestUtils.md5Hex(timeStamp + PRIVATE_KEY + PUBLIC_KEY);
    }

    public ComicsMarvelService (MarvelComicsClient client) {
        this.client = client;
    }
}
