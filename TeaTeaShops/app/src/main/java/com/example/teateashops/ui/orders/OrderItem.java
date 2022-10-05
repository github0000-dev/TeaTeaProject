package com.example.teateashops.ui.orders;

public class OrderItem {
    public String name;
    public double price,totalprice;
    public int quantity;

    public OrderItem () {

    }

    public OrderItem(String name,double price,int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

}
