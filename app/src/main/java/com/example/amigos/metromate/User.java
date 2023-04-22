package com.example.amigos.metromate;

public class User {
    private String name;
    private String email;
    private String phone;
    private double balance;


    public User() {
    }

    public User(String name, String email, String phone, double balance) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    // Getters and setters for the fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}