// package com.example.Banking_Application;

// import java.math.BigDecimal;

// import org.hibernate.validator.constraints.NotBlank;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import jakarta.validation.constraints.Size;


// @Controller
// public class BankController {
//     // @Autowired
//     // private PasswordEncoder passwordEncoder;
//     Account account2 = new Account();
//     @Autowired
//   public AccountService accountService;
//   @Autowired
//   public AccountRepository accountRepository;
//   String username2=new String();
// // @GetMapping("/dashboard")
// // public String dashboard() {
// // return"/dashboard";
// // }
//     @GetMapping("/dashboard")
//     public String dashboard(Model model) {
//         Account account = accountService.findByUsername(account2.getUsername());
//        model.addAttribute("username", account.getUsername().toUpperCase());
//         model.addAttribute("balance", account.getBalance());
//         model.addAttribute("myid",account.getMyid()); 
//         return "/dashboard";
//     }
//     @GetMapping("/register")
//     public String showRegistrationForm() {
//         return "register"; 
//     }
// //     @PostMapping("/register")
// //       public String registerAccount(@RequestParam String username,@RequestParam String password,Model model) {
// //         // public void registerAccount(@RequestBody Account account){
// //        try{
// //         System.out.println("username"+username);
// //         accountService.regisAccount(username, password);
// //       return"redirect:/login";
// //       }
// // // accountRepository.save(account);
// //     //   System.out.println("Account created successfully");
// //         // return "redirect:/login";
// //       catch (Exception e) {
// //          model.addAttribute("error", e.getMessage());
// //          return "register";}
// //     }

//  @PostMapping("/register")
//     public String registerAccount(
//             @RequestParam  String username,
//             @RequestParam  String password,
//             Model model,
//             RedirectAttributes redirectAttributes) {
        
//         try{
//             System.out.println("username"+username);
//             accountService.regisAccount(username, password);
//             redirectAttributes.addFlashAttribute("success", "Account created successfully. Please login.");
//             return "redirect:/login";
            
//         } catch (Exception e) {
//             model.addAttribute("error", "Registration failed: " + e.getMessage());
//             return "register";
//         }
//     }
//     @GetMapping("/login")
//      public String showLoginForm() {
//          return "login";
//      }
//     @PostMapping("/login")
//     public String login(@RequestParam String username, @RequestParam String password, Model model) {
//         account2.setUsername(username);
//         Account account = accountService.findByUsername(username);
//     //    System.out.println(account.getUsername());
//         // System.out.println(account.getPassword());
// try {
//    if(( account.getPassword().equals(password))){
//         return "redirect:/dashboard";
//     }
// }
//        catch (Exception e) {
//         model.addAttribute("error", e.getMessage());
//         return "login";
//         }
            
        
//      return "redirect:/dashboard";
//     }
//     @PostMapping("/deposit")
//     public String deposit(@RequestParam BigDecimal amount) {
      
      
//         Account account = accountService.findByUsername(account2.getUsername());
//         System.out.println( "account="+account.getUsername());
//         accountService.deposit(account2.getUsername(), amount);
//         return "redirect:/dashboard";
//     }
//    @PostMapping("/withdraw")
//     public String withdraw(@RequestParam BigDecimal amount, Model model) {
//         // String username=SecurityContextHolder.getContext().getAuthentication().getName();
//         Account account = accountService.findByUsername(account2.getUsername());
//       try { accountService.withdraw(account2.getUsername(), amount);}
//         catch (Exception e) {
//             model.addAttribute("error", e.getMessage());
//             model.addAttribute("account", account);
//         return "dashboard";
//         }
//      return "redirect:/dashboard";
//  }
//     @GetMapping("/transactions")
//     public String transactionHistory(Model model) {
//         // String username=SecurityContextHolder.getContext().getAuthentication().getName();
//         Account account = accountService.findByUsername(account2.getUsername());
//         model.addAttribute("transactions", accountService.getTransactionsHistory(account));
//         return "transactions";
//     }
//     @GetMapping("/logout")
//     public String logout() {
//         return "redirect:/login";
//     }
//      @PostMapping("/transfer")
//   public String transfer(@RequestParam String username, @RequestParam BigDecimal amount, Model model) {
//     //   String username1=SecurityContextHolder.getContext().getAuthentication().getName();
//       Account fromAccount = accountService.findByUsername(account2.getUsername());
//       try {
//         accountService.transferAmount(fromAccount, username, amount);
//     }
//       catch (Exception e) {
//           model.addAttribute("error", e.getMessage());
//           model.addAttribute("account", fromAccount);
//           return "dashboard";
//       }
//       return "redirect:/dashboard";
//   }
// }
package com.example.Banking_Application;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BankController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        String username = getCurrentUsername();
        Account account = accountService.findByUsername(username);
        model.addAttribute("username", account.getUsername().toUpperCase());
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("myid", account.getMyid());
        return "dashboard";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerAccount(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Hash the password before saving
            String hashedPassword = passwordEncoder.encode(password);
            accountService.regisAccount(username, hashedPassword);
            redirectAttributes.addFlashAttribute("success", "Account created successfully. Please login.");
            return "redirect:/login";
            
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam BigDecimal amount) {
        String username = getCurrentUsername();
        accountService.deposit(username, amount);
        return "redirect:/dashboard";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam BigDecimal amount, Model model) {
        String username = getCurrentUsername();
        try {
            accountService.withdraw(username, amount);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            Account account = accountService.findByUsername(username);
            model.addAttribute("account", account);
            return "dashboard";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/transactions")
    public String transactionHistory(Model model) {
        String username = getCurrentUsername();
        Account account = accountService.findByUsername(username);
        model.addAttribute("transactions", accountService.getTransactionsHistory(account));
        return "transactions";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String username, @RequestParam BigDecimal amount, Model model) {
        // String fromUsername = getCurrentUsername();
        // Account fromAccount = accountService.findByUsername(fromUsername);
        try {
            String fromUsername = getCurrentUsername();
        Account fromAccount = accountService.findByUsername(fromUsername);
            accountService.transferAmount(fromAccount, username, amount);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            // model.addAttribute("account",accountService.findByUsername( getCurrentUsername()));
             Account account = accountService.findByUsername(getCurrentUsername());
        model.addAttribute("username", account.getUsername().toUpperCase());
        model.addAttribute("balance", account.getBalance());
        model.addAttribute("myid", account.getMyid());
             return "dashboard";
        }
        return "redirect:/dashboard";
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}