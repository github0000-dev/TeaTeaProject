package com.example.teateashops;

public class Shop {
    public String owner_name,shop_name,shop_address,shop_desc,shop_position,id;

    public Shop() {

    }

    public Shop(String shop_position,String shop_name,String shop_address,String shop_desc) {
        this.shop_position = shop_position;
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_desc = shop_desc;
    }
}
