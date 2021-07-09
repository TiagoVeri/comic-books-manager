package com.marvel.zuptest.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String msg){
        super(msg);
    }
    private EntityNotFoundException(){}
}
