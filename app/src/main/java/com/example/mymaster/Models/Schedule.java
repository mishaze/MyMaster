package com.example.mymaster.Models;

public class Schedule {
    private String time_start="1", time_finish="2";

    Schedule(){}

    public void setTime_finish(String time_finish) {
        this.time_finish = time_finish;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_finish() {
        return time_finish;
    }

    public String getTime_start() {
        return time_start;
    }
}
