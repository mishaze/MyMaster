package com.example.mymaster;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymaster.Models.Schedule;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ScheduleSettingActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int mHour = cal.get(Calendar.HOUR_OF_DAY);
    int mMinute = cal.get(Calendar.MINUTE);
    private DatabaseReference mDatabase;

    ArrayList<TextView> Schedule7 = new ArrayList<>();
    ArrayList<Schedule> schedule = new ArrayList<>();
    TextView dateFrom, dateTo;
    Button save;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_setting);

        mDatabase = FirebaseDatabase.getInstance().getReference("Masters");

        save = findViewById(R.id.btn_sch_Save);
        root = findViewById(R.id.root_sch_set);
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

        onClickDate(dateFrom);
        onClickDate(dateTo);

        for (TextView v : Schedule7) {
            onClickTime(v);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTimeInDb(Schedule7);
                mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("schedule")
                        .setValue(schedule).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(root, "Расписание добавлено", Snackbar.LENGTH_SHORT).show();
                        schedule.clear();
                    }
                });

            }
        });
    }

    private void onClickTime(final TextView v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleSettingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String textViewTimeParam;
                                if(minute<10) {
                                    textViewTimeParam = hourOfDay + ":0" + minute;
                                }
                                else{
                                    textViewTimeParam = hourOfDay + ":" + minute;

                                }
                                v.setText(textViewTimeParam);
                            }
                        },
                        mHour,
                        mMinute,
                        true);
                timePickerDialog.show();
            }
        });
    }

    private void onClickDate(final TextView v) {
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

    private void setTimeInDb(ArrayList<TextView> v) {
        for (int i = 0; i <=6; i++) {

            String tempStart = v.get(i).getText().toString();
            String tempStop = v.get(i+7).getText().toString();

            int timeStart = timeParse(tempStart)+(24*60*i);
            int timeStop = timeParse(tempStop)+(24*60*i);

            schedule.add(new Schedule(timeStart,timeStop));
        }
    }

    private int timeParse(String temp)
    {
        int time = 0;
        if (temp.indexOf(':') == 1) {
            time = Integer.parseInt(temp.substring(0, 1)) * 60;
            time = time + Integer.parseInt(temp.substring(2, 4));
        }
        else if (temp.indexOf(':') == 2) {
            time = Integer.parseInt(temp.substring(0, 2)) * 60;
            time = time + Integer.parseInt(temp.substring(3, 5));
        }
        return time;
    }
}