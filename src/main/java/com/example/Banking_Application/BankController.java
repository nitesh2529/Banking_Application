
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
