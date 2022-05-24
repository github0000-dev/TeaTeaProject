package com.example.teateadelivery;

public class Order {
    public String order_id,cust_id,deliverer_id,status;
    public Order() {}
    public Order(String order_id,String cust_id,String deliverer_id,String status) {
        this.order_id = order_id;
        this.cust_id = cust_id;
        this.deliverer_id = deliverer_id;
        this.status = status;
    }
}
