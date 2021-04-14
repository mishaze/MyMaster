package com.example.mymaster.Models;

import java.util.Objects;

public class ScheduleItem {
    private String name, services, time_start, time_end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScheduleItem(String name) {
        this.name = name;
    }

    public ScheduleItem(String name, String services, String time_start, String time_end) {
        this.name = name;
        this.services = services;
        this.time_start = time_start;
        this.time_end = time_end;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleItem that = (ScheduleItem) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(services, that.services) &&
                Objects.equals(time_start, that.time_start) &&
                Objects.equals(time_end, that.time_end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, services, time_start, time_end);
    }
}



