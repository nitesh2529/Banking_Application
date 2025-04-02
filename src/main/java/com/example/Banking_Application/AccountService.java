package com.example.Banking_Application;

import java.math.BigDecimal;
import java.security.AuthProvider;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.UserDetailsServiceConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

 @Service
@Component
public class AccountService {
   @Autowired
   public AccountRepository accountRepository;
//     @Autowired
@Autowired
AccountService accountService;
@Autowired
// Transaction transaction;
  public TransactionRepository transactionRepository;
//  Account account = new Account();
     public Account findByUsername(String username){
       
        List<Account> accounts = accountRepository.findAll();
        // System.out.println(accounts);
        for(Account account: accounts){
            // System.out.println(account.getUsername());
            if(account.getUsername().equals(username)){
                // System.out.println("Account found");
                return account;
            }
    //  else{
    //     throw new RuntimeException("Account not found");
    //  }
    }
     
     return null;
    }
//     public Transaction findAccountById(Long id){
//         return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));}

public void regisAccount(String username, String password){
  if(accountService.findByUsername(username)!=null){
      throw new RuntimeException("Account already exists");
  }
    Account account = new Account();
     account.setMyid(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
    account.setUsername(username);
    account.setPassword(password);
    account.setBalance(BigDecimal.ZERO);
    accountRepository.save(account);
    
}

    public void deposit(String username, BigDecimal amount){
        Account account =accountService.findByUsername(username);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        // Transaction transaction = new Transaction( 
        //     amount,
        //     "DEPOSIT",
        //     LocalDateTime.now(),
        //     account
        // );
        // transactionRepository.save(transaction);
    }
    public void withdraw(String username, BigDecimal amount){
        Account account = accountService.findByUsername(username);
        if(account.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
        // public boolean login(String username, String password){
        // Account account = findByUsername(username);
        // return passwordEncoder.matches(password, account.getPassword());}
        // account.setBalance(account.getBalance().subtract(amount));
        // accountRepository.save(account);
        // Transaction transaction = new Transaction(
        //     amount,
        //     "WITHDRAW",
        //     LocalDateTime.now(),
        //     account
        // );
        // transactionRepository.save(transaction);
    
    public List <Transaction> getTransactionsHistory(Account account2){

        Account account = findByUsername(account2.getUsername());
// System.out.println(account.getTransactions());
        return account.getTransactions();
    }
    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     Account account = accountService.findByUsername(username);
    //    if(account == null){
    //        throw new UsernameNotFoundException("Account not found");
    //    }
    //    return new Account();
    // }
    // public Collection<? extends GrantedAuthority>authorities(){
    //     return Arrays.asList(new SimpleGrantedAuthority("User"));
    // }
    public void transferAmount(Account fromAccount,String toUsername, BigDecimal amount){
        if(fromAccount.getBalance().compareTo(amount)<0){
            throw new RuntimeException("Insufficient fund");
        }
        Account toAccount = accountService.findByUsername(toUsername);
        // .orElseThrow(() -> new RuntimeException("Account not found"));
        //withdraw from fromAccount
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        // accountRepository.save(fromAccount);
        //add to toAccount
        toAccount.setBalance(toAccount.getBalance().add(amount));
        // accountRepository.save(toAccount);
        //record transaction
       Transaction debitTransaction = new Transaction();
        //    amount,
        //    "TRANSFER"+" to "+toAccount.getUsername(),
        //    LocalDateTime.now(),
        //    fromAccount
       debitTransaction.setAmount(amount);
         debitTransaction.setType("TRANSFER"+" From "+fromAccount.getUsername().toUpperCase()+" To "+toAccount.getUsername().toUpperCase());
            debitTransaction.setTimestamp(LocalDateTime.now());
            debitTransaction.setFromAcc(fromAccount.getMyid());
            debitTransaction.setToAcc(toAccount.getMyid());

           
         Transaction transaction1= transactionRepository.save(debitTransaction);
toAccount.getTransactions().add(transaction1);
        accountRepository.save(toAccount);
        // accountRepository.save(fromAccount);
    //    Transaction creditTransaction = new Transaction();
    //    creditTransaction.setAmount(amount);
    //    debitTransaction.setType("TRANSFER"+" from "+fromAccount.getUsername().toUpperCase());
        //   creditTransaction.setTimestamp(LocalDateTime.now());

        //   Transaction transaction2= transactionRepository.save(debitTransaction);
            fromAccount.getTransactions().add(transaction1);
        //  toAccount.setTransactions(Arrays.asList(creditTransaction));
        accountRepository.save(fromAccount);
    }
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     Account account = accountService.findByUsername(username);
    //    if(account == null){
    //        throw new UsernameNotFoundException("Account not found");
    //    }
    //    return new Account();
    // } 
    // public Collection<? extends GrantedAuthority>authorities(){
    //         return Arrays.asList(new SimpleGrantedAuthority("User"));
    //     }
}

