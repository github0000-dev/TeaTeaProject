package com.example.teateaadmins.ui.customers;

public class Customer {
    public String name,address,email,number,username,password,id;

    public Customer() {

    }

    public Customer(String name, String address, String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Customer(String name, String address, String email, String number, String username, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
        this.username = username;
    }
    public Customer(String name, String address, String email, String number, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public void getName(String n) {
        this.name = n;
    }

    public String getCustID() {
        return id;
    }
}
