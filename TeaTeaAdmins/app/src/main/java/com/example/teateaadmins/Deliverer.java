package com.example.teateaadmins;

public class Deliverer {
    public String name,address,email,number,username,password,id;

    public Deliverer() {

    }

    public Deliverer(String name, String address, String id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }

    public Deliverer(String name, String address, String email, String number, String username, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
        this.username = username;
    }
    public Deliverer(String name, String address, String email, String number, String password) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.number = number;
        this.password = password;
    }

    public void getName(String n) {
        this.name = n;
    }

    public String getDelID() {
        return id;
    }
}
