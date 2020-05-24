package com.svceindore.minor.repositories;

import com.svceindore.minor.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<Token,String> {
    Token findByEmail(String email);
}

