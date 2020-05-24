package com.svceindore.minor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Book  {
    @Id
    private String id;
    private String title;
    private String issuedTo;
    private String author,bookId;
    private Date issuedOn,SubmittedOn;
    private int fine;

    public Book() {

    }

    public Book(String id, String title, String author, String bookId, Date issuedOn, Date submittedOn, int fine) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.issuedOn = issuedOn;
        this.SubmittedOn = submittedOn;
        this.fine = fine;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    public Date getSubmittedOn() {
        return SubmittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        SubmittedOn = submittedOn;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book[" + "id='" + id + '\'' +", title='" + title + '\'' + ", author='" + author + '\'' + ']';
    }
}
