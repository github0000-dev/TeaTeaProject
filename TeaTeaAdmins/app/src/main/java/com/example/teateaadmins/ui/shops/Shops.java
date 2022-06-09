package com.example.teateaadmins.ui.shops;

public class Shops {
    public String address,contact,name,shopid;

//    list.add(new Shops(nameGetter,addGetter,String.valueOf(i)));

    public Shops(String name,String address,String shopid) {
        this.name = name;
        this.address = address;
        this.shopid = shopid;
    }
}
