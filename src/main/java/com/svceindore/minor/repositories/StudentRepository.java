package com.svceindore.minor.repositories;

import com.svceindore.minor.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    Student findByEmail(String email);
}

