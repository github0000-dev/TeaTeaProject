package com.example.teatea;

public class Cart {
    public int prod_id,quantity;
    public String teasize,name;
    public double price,totalprice;

    public Cart(){}

    public Cart(String name,String teasize,int quantity,double totalprice) {
        this.name = name;
        this.quantity = quantity;
        this.teasize = teasize;
        this.totalprice = totalprice;
    }

//    public Cart(int prod_id,int quantity,int size,double price,double totalprice){
//        this.prod_id = prod_id;
//        this.quantity = quantity;
//        this.size = size;
//        this.price = price;
//        this.totalprice = totalprice;
//    }

    public Cart(int prod_id,int quantity,String teasize,double price,double totalprice,String name){
        this.prod_id = prod_id;
        this.quantity = quantity;
        this.teasize = teasize;
        this.price = price;
        this.totalprice = totalprice;
        this.name = name;
    }

    public Cart(int quantity,String teasize,double price,double totalprice,String name){
        this.quantity = quantity;
        this.teasize = teasize;
        this.price = price;
        this.totalprice = totalprice;
        this.name = name;
    }
}
