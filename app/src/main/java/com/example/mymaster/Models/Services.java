package com.example.mymaster.Models;

import java.util.Objects;

public class Services {
    String name ,
            time ,
            price ;


    public Services() {
    }

    public Services(String name, String price,String time) {
        this.name = name;
        this.time = time;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return Objects.equals(name, services.name) &&
                Objects.equals(time, services.time) &&
                Objects.equals(price, services.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, time, price);
    }
}
