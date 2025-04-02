package com.example.Banking_Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
 @Repository
public interface AccountRepository extends MongoRepository<Account, Long> {
    // Optional<Account> findByUsername(String username);

//    Optional<Account> findAccountById(Long id);

    
}
