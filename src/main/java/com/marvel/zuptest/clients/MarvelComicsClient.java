package com.marvel.zuptest.clients;

import com.marvel.zuptest.controllers.response.ComicsMarvelResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "marvel", url = "${url.marvel}/v1/public", decode404 = true)
public interface MarvelComicsClient {
    @GetMapping("/comics")
    ComicsMarvelResponse getAll(@RequestParam(value = "ts") Long timeStamp,
                                       @RequestParam(value = "apikey") String publicKey, @RequestParam(value = "hash") String hashMD5);

    @GetMapping("/comics/{comicId}")
    ComicsMarvelResponse getById(@PathVariable Integer comicId,
                                        @RequestParam(value = "ts") Long timeStamp,
                                        @RequestParam(value = "apikey") String publicKey, @RequestParam(value = "hash") String hashMD5);

}