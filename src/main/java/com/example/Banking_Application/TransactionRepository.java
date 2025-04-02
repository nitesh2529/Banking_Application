 package com.example.Banking_Application;

 import java.util.List;
 import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
 import org.springframework.stereotype.Component;
 import org.springframework.stereotype.Repository;
  @Repository
 public interface TransactionRepository extends MongoRepository<Transaction, Long> {
   //   List<Transaction> findByAccountId(ObjectId Id);

    //    Optional<Account> findAccountById(Long id);

    //   List<Transaction> findByAccountId(Long id);

    //    Optional<Account> findAccountById(Long id);
    
 }
