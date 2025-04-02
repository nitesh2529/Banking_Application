//  package com.example.Banking_Application;

//  import org.springframework.beans.factory.annotation.Autowired;
//  import org.springframework.context.annotation.Bean;
//  import org.springframework.context.annotation.Configuration;
//  import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// // Removed unused import causing visibility issues
//   @Configuration
//    @EnableWebSecurity
//   public class SecurityConfig {
//       @Autowired
//       AccountService accountService;
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//  }
  
 
//    @SuppressWarnings({ "deprecation", "removal" })
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//        .csrf(crsf -> crsf.disable())
//        .authorizeRequests(authz -> authz
//        .requestMatchers("/register","/login").permitAll()
//        .anyRequest().authenticated())
//        .formLogin(form -> form
//        .loginPage("/login")
//        .loginProcessingUrl("/login")
//        .defaultSuccessUrl("/dashboard", true)
//        .permitAll())
//        .logout(logout -> logout
//        .invalidateHttpSession(true)
//        .clearAuthentication(true)
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//        .logoutSuccessUrl("/login?logout")
//        .permitAll())
//        .headers(header -> header.frameOptions().sameOrigin());
//        return http.build();
//    }
// //   @Autowired
// //   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// //       auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
// //   }
//    }