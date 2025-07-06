package com.example.Banking_Application;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 @Data
 @Getter
 @Setter
 @AllArgsConstructor
 @NoArgsConstructor
@Document(collection = "accounts")
public class Account  {
    @Id
    // private ObjectId id;
  private long myid;
    private String username;
    private String password;
    private BigDecimal balance;
    @DBRef  
    private List<Transaction> transactions=new ArrayList<>();
 
}
