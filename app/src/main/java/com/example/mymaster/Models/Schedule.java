package com.example.mymaster.Models;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Objects;


public class Schedule {
    private int time_start = 1, time_finish = 1;
    boolean isEnable = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return time_start == schedule.time_start &&
                time_finish == schedule.time_finish &&
                isEnable == schedule.isEnable;
    }

    @Override
    public int hashCode() {
        return Objects.hash(time_start, time_finish, isEnable);
    }

    public Schedule() {
    }

    public Schedule(int start, int finish, boolean isEnable) {
        setTime_start(start);
        setTime_finish(finish);
        setEnable(isEnable);
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public int getTime_start() {
        return time_start;
    }

    public void setTime_start(int time_start) {
        this.time_start = time_start;
    }

    public int getTime_finish() {
        return time_finish;
    }

    public void setTime_finish(int time_finish) {
        this.time_finish = time_finish;
    }
}
