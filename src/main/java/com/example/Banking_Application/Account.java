package com.example.Banking_Application;

import org.springframework.data.annotation.Transient;
import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsAwareConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;
 @Data
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
  //  @Override
  //  public Collection<? extends GrantedAuthority> getAuthorities() {
  //     // TODO Auto-generated method stub
  //      throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
  //  }
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    // }
}
