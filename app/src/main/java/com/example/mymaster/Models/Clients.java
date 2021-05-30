package com.example.mymaster.Models;

import java.util.Objects;

public class Clients {
    private String
            first_name = "";
    private String second_name = "";
    private String email = "";
    private String phone = "";
    private String uid="";

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Clients() {
    }

    public Clients(String first_name, String second_name, String email, String phone) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Objects.equals(first_name, clients.first_name) &&
                Objects.equals(second_name, clients.second_name) &&
                Objects.equals(email, clients.email) &&
                Objects.equals(phone, clients.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, second_name, email, phone);
    }
}
