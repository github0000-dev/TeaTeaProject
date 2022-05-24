package com.example.teatea;

import android.media.Image;

public class MilkteaItem {
    public String name,description,price_s,price_m,price_l,datereleased,totalitem_price,size_price,quantity_item,shop_from,id,shop_id;


    public MilkteaItem () {

    }
    public MilkteaItem(String name,String shop_from,String id) {
        this.name = name;
        this.shop_from = shop_from;
        this.id = id;
    }
    public MilkteaItem (String name,String description,String price_s,String price_m,String price_l,String datereleased) {
        this.name = name;
        this.description = description;
        this.price_s = price_s;
        this.price_m = price_m;
        this.price_l = price_l;
        this.datereleased = datereleased;
    }


    public MilkteaItem(String name,String size_price,String quantity_item,String totalitem_price) {
        this.name = name;
        this.totalitem_price = totalitem_price;
        this.size_price = size_price;
        this.quantity_item = quantity_item;

    }

    public String getProdName() {
        return name;
    }

    public String getId() {
        return id;
    }



}
