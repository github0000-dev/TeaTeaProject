package com.example.teateaadmins;

public class Vendor {
    public String name,address,email,number,username,password,id;

    public Vendor() {

    }

    public Vendor(String name, String address, String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Vendor(String name, String address, String email, String number, String username, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
        this.username = username;
    }
    public Vendor(String name, String address, String email, String number, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public void getName(String n) {
        this.name = n;
    }

    public String getVendID() {
        return id;
    }
}
