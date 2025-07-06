package com.example.Banking_Application;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminSer {
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
   public void regisAdmin(String username, String password){

    Admin admin = new Admin();
    admin.setUsername(username);
    admin.setPassword(password);
    adminRepo.save(admin);
    
}
public  Admin findByUsername(String username){
       
    List<Admin>list=adminRepo.findAll();
    for( Admin account: list){
        if(account.getUsername().equals(username)){
            return account;
        }

}
 
 return null;
}
public Transaction transactions(String id){
    List<Transaction>transaction=transactionRepository.findAll();
    for( Transaction account: transaction){
        if(account.getId().toString().equals(id)){
            // System.out.println("Account found");
            return account;
        }
    
}
return null;
}
 public Account AccountDetail(String id){
    List<Account>detail=accountRepository.findAll();
    for( Account account: detail){
        if(String.valueOf(account.getMyid()).equals(id)){
            // System.out.println("Account found");
            return account;
        }
    
}
return null;
 }
}
