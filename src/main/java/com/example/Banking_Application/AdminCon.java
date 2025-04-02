package com.example.Banking_Application;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminCon {
    @Autowired
    AdminSer adminSer;
    @Autowired
    AdminRepo adminRepo;
    @GetMapping("/loginAdmin") 
    public String check(){
        return"loginAdmin";
    }
    @PostMapping("/loginAdmin") 
    public String checkuser(@RequestParam String username,@RequestParam String password){
        
        if(adminSer.findByUsername(username)!=null){
return"AdminDashboard";
        }
        return"loginAdmin";
    }
      @PostMapping("/registerAdmin")
      public String registerAdmin(@RequestParam String username,@RequestParam String password,Model model) {
        // public void registerAccount(@RequestBody Account account){
    //    try{
        adminSer.regisAdmin(username, password);
      return"redirect:loginAdmin";
    //   }
// accountRepository.save(account);
    //   System.out.println("Account created successfully");
    //     // return "redirect:/login";
    //   catch (Exception e) {
    //      model.addAttribute("error", e.getMessage());
    //      return "register";}
    }
    @PostMapping("/transactionById")
    public String transactionHistory(@RequestParam String id,Model model) {
        // String username=SecurityContextHolder.getContext().getAuthentication().getName();
       
        model.addAttribute("transactions",adminSer.transactions(id));
        return "transactionView";
    }
    @PostMapping("/AccNo")
    public String Detail(@RequestParam String id,Model model) {
        // Account account=adminSer.AccountDetail(id);
        // String username=SecurityContextHolder.getContext().getAuthentication().getName();
    //   if(account==null){
    //     re
    //   }
    try {
        if (adminSer.AccountDetail(id)!=null) {
            // System.out.println("i am inside the admin"+adminSer.AccountDetail(id));
            Account account =adminSer.AccountDetail(id) ;
            model.addAttribute("username", account.getUsername().toUpperCase());
             model.addAttribute("balance", account.getBalance());
             model.addAttribute("myid",account.getMyid()); 
             model.addAttribute("transactions",account.getTransactions());
            return "UserDetail";
        }
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
    }  
       return "transactionView";
    }
    @GetMapping("/AdminDashboard")
    public String showRegistration() {
        return "AdminDashboard"; 
    }
     @GetMapping("/registerAdmin")
     public String showRegistrationForm() {
         return "registerAdmin"; 
     }
    
}
