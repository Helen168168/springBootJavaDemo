package com.example.springbootdemo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class User {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    // Getter & Setter
}
