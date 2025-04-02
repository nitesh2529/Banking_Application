package com.example.Banking_Application;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "adminsevice")
public class Admin {
    String username;
    String password;
}
