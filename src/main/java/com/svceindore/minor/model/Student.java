package com.svceindore.minor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Student {
    @Id()
    private String id;
    private String name,email,branchId;
    private List<Book> transaction;

    public Student() {
    }

    public Student(String id, String name, String email, String branchId, List<Book> transaction) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branchId = branchId;
        this.transaction = transaction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public List<Book> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Book> transaction) {
        this.transaction = transaction;
    }

    public Student(String id, String username, String password) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", branchId='" + branchId + '\'' +
                ", transaction=" + transaction +
                '}';
    }
}
