package com.marvel.zuptest.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comics {

    @Id
    private Integer comicId;
    private String title;
    private BigDecimal price;
    private String isbn;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @ElementCollection
    private Set<String> creators = new HashSet<>();

    public Comics() {
    }

    public Comics(Integer comicId, String title, BigDecimal price, String isbn, String description) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Set<String> getCreators() {
        return creators;
    }

    public void setCreators(Set<String> creators) {
        this.creators = creators;
    }
}
