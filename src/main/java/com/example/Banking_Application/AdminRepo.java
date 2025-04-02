package com.example.Banking_Application;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdminRepo extends MongoRepository<Admin, Long> {

    
} 

