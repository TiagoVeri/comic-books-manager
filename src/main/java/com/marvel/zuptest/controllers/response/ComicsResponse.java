package com.marvel.zuptest.controllers.response;

public class ComicsResponse {
    private String copyright;
    private String attributionHTML;

    private DataResponse data;

    public String getCopyright() {
        return copyright;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public DataResponse getData() {
        return data;
    }
}
