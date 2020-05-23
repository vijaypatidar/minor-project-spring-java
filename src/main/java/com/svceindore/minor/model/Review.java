package com.svceindore.minor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Review {
    @Id
    private String id;
    private String email;
    private int rating;
    private String review;

    public Review() {
    }

    public Review(String id, String email, int rating, String review) {
        this.id = id;
        this.email = email;
        this.rating = rating;
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                '}';
    }
}
