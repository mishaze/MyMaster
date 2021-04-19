package com.example.mymaster.Models;

import java.util.Objects;

public class RecordingSession {
    private String id_master ,
                    id_client,
                    date,
                    price,
                    service,
                    day_week,
                    end_service,
                    start_service;


    public RecordingSession() {
    }

    public RecordingSession(String id_master, String id_client, String date, String price, String service, String day_week, String end_service, String start_service) {
        this.id_master = id_master;
        this.id_client = id_client;
        this.date = date;
        this.price = price;
        this.service = service;
        this.day_week = day_week;
        this.end_service = end_service;
        this.start_service = start_service;
    }

    public String getId_master() {
        return id_master;
    }

    public void setId_master(String id_master) {
        this.id_master = id_master;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDay_week() {
        return day_week;
    }

    public void setDay_week(String day_week) {
        this.day_week = day_week;
    }

    public String getEnd_service() {
        return end_service;
    }

    public void setEnd_service(String end_service) {
        this.end_service = end_service;
    }

    public String getStart_service() {
        return start_service;
    }

    public void setStart_service(String start_service) {
        this.start_service = start_service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordingSession that = (RecordingSession) o;
        return Objects.equals(id_master, that.id_master) &&
                Objects.equals(id_client, that.id_client) &&
                Objects.equals(date, that.date) &&
                Objects.equals(price, that.price) &&
                Objects.equals(service, that.service) &&
                Objects.equals(day_week, that.day_week) &&
                Objects.equals(end_service, that.end_service) &&
                Objects.equals(start_service, that.start_service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_master, id_client, date, price, service, day_week, end_service, start_service);
    }
}
