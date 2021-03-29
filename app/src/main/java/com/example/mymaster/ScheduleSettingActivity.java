package com.example.mymaster;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymaster.Models.Schedule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ScheduleSettingActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int mHour = cal.get(Calendar.HOUR_OF_DAY);
    int mMinute = cal.get(Calendar.MINUTE);

    ArrayList<TextView> Schedule7 = new ArrayList<>();
    int[][] list = new int[7][2];
    TextView dateFrom, dateTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_setting);

        Schedule7.add((TextView) findViewById(R.id.sch_from1));
        Schedule7.add((TextView) findViewById(R.id.sch_from2));
        Schedule7.add((TextView) findViewById(R.id.sch_from3));
        Schedule7.add((TextView) findViewById(R.id.sch_from4));
        Schedule7.add((TextView) findViewById(R.id.sch_from5));
        Schedule7.add((TextView) findViewById(R.id.sch_from6));
        Schedule7.add((TextView) findViewById(R.id.sch_from7));
        Schedule7.add((TextView) findViewById(R.id.sch_to1));
        Schedule7.add((TextView) findViewById(R.id.sch_to2));
        Schedule7.add((TextView) findViewById(R.id.sch_to3));
        Schedule7.add((TextView) findViewById(R.id.sch_to4));
        Schedule7.add((TextView) findViewById(R.id.sch_to5));
        Schedule7.add((TextView) findViewById(R.id.sch_to6));
        Schedule7.add((TextView) findViewById(R.id.sch_to7));

        dateFrom = findViewById(R.id.sch_data_from);
        dateTo = findViewById(R.id.sch_data_to);

        OnClickDate(dateFrom);
        OnClickDate(dateTo);

        for(TextView v:Schedule7) {
            OnClickTime(v);
        }
    }

    private void OnClickTime(final TextView v)
    {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleSettingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String editTextTimeParam = hourOfDay + " : " + minute;
                                v.setText(editTextTimeParam);
                            }
                        },
                        mHour,
                        mMinute,
                        true);
                timePickerDialog.show();
            }
        });
    }

    private void OnClickDate(final TextView v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ScheduleSettingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String editTextDateParam = dayOfMonth + "." + (monthOfYear + 1) + "." + year;
                                v.setText(editTextDateParam);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}