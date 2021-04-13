package com.example.mymaster.Models;

public class RecordingSession {
    private String id_friends = "",
                    service = "",
                    date = "";

    private boolean is_comlete;

    public RecordingSession() {
    }

    public String getId_friends() {
        return id_friends;
    }

    public void setId_friends(String id_friends) {
        this.id_friends = id_friends;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIs_comlete() {
        return is_comlete;
    }

    public void setIs_comlete(boolean is_comlete) {
        this.is_comlete = is_comlete;
    }
}
