package com.example.teateashops;

import android.media.Image;

public class Product {
    public String name,price,date_rel,shop_from,desc;
    public Image image;

    public Product(String price, String desc) {
        this.desc = desc;
        this.price = price;
    }
    public Product(String name, String price, String shop_from,String date_rel) {
        this.name = name;
        this.price = price;
        this.shop_from = shop_from;
        this.date_rel = date_rel;
    }
    public Product() {

    }

}

