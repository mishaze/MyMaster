package com.example.mymaster.Models;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class User {


    private String first_name ="";
    private String second_name ="";
    private String email ="";
    private String phone ="";
    private String address ="";
    private String info ="";

    ArrayList<String> friends = new ArrayList<>();
    ArrayList<String> List_services = new ArrayList<>();
    ArrayList<Schedule> schedule = new ArrayList<>();

    public User() {
    }

    public User(String name, String email, String pass, String phone) {
        this.first_name = name;
        this.email = email;
        this.phone = phone;
    }

    private String
            uid="";

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getInfo() {
        return info;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public void setList_services(ArrayList<String> list_services) {
        List_services = list_services;
    }

    public ArrayList<String> getList_services() {
        return List_services;
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) &&
                Objects.equals(first_name, user.first_name) &&
                Objects.equals(second_name, user.second_name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(address, user.address) &&
                Objects.equals(info, user.info) &&
                Objects.equals(List_services, user.List_services) &&
                Objects.equals(schedule, user.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, first_name, second_name, email, phone, address, info, List_services, schedule);
    }
}
