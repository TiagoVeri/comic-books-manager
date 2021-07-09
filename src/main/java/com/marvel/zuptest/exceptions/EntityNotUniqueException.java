package com.marvel.zuptest.exceptions;

public class EntityNotUniqueException extends RuntimeException{

    public EntityNotUniqueException(String msg){
        super(msg);
    }
    private EntityNotUniqueException(){}
}
