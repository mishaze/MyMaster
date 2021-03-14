package com.example.mymaster.Models;

public class Services {
    private String service;
    int price;
    public Services(){}

    public void setPrice(int price) {
        this.price = price;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getPrice() {
        return price;
    }

    public String getService() {
        return service;
    }
}
