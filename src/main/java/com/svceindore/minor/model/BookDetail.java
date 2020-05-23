package com.svceindore.minor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class BookDetail {
    @Id
    private String id;
    private String title;
    private String authors;
    private String isbn;
    private int quantity, available;
    private List<Review> reviews;

    public BookDetail() {

    }

    public BookDetail(String id, String title, String authors, String ISBN, int quantity, int available, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.isbn = ISBN;
        this.quantity = quantity;
        this.available = available;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", ISBN='" + isbn + '\'' +
                ", quantity=" + quantity +
                ", available=" + available +
                ", reviews=" + reviews +
                '}';
    }
}
