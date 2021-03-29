package com.example.mymaster.Models;

import android.util.Pair;
import java.util.ArrayList;


public class Schedule {
    private int time_start = -1, time_finish = -1;

   public Schedule() {
    }

   public Schedule(int start, int finish) {
        setTime_start(start);
        setTime_finish(finish);
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
