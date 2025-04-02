 package com.example.Banking_Application;

 import java.math.BigDecimal;
 import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;
 @Data
//   @AllArgsConstructor
//   @NoArgsConstructor
 @Document(collection = "transactions")
 public class Transaction {
    // public Transaction(BigDecimal amount, String string, LocalDateTime now, Account account) {
    //     TODO Auto-generated constructor stub
    // }
    @Id
   private ObjectId id;
   @Indexed(unique = true)
     private BigDecimal amount;
     private String type;
     private long fromAcc;
     private long toAcc;
     private LocalDateTime timestamp;
 }
