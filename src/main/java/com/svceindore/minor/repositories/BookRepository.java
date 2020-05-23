package com.svceindore.minor.repositories;

import com.svceindore.minor.model.BookDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookDetail,String> {
}
