package com.marvel.zuptest.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comics {

    @Id
    private Integer comicId;
    private String title;
    private Float price;
    private String isbn;
    private String description;

    @ManyToMany(mappedBy="comics")
    private List<User> users = new ArrayList<>();

    //private List<?> creators = new ArrayList<>();

    public Comics() {
    }

    public Comics(Integer comicId, String title, Float price, String isbn, String description) {
        this.comicId = comicId;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.description = description;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
