package com.example.mymaster.Schedule;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mymaster.Models.Schedule;
import com.example.mymaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class ScheduleSettingActivity extends AppCompatActivity {

    Calendar cal = Calendar.getInstance();
    int mHour = cal.get(Calendar.HOUR_OF_DAY);
    int mMinute = cal.get(Calendar.MINUTE);
    private DatabaseReference mDatabase;

    ArrayList<TextView> textViews = new ArrayList<>();
    ArrayList<Schedule> schedule = new ArrayList<>();
    ArrayList<CheckBox> checkBoxes = new ArrayList<>();

    TextView dateFrom, dateTo;
    Button save;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_setting);

        mDatabase = FirebaseDatabase.getInstance().getReference("Masters").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

        save = findViewById(R.id.btn_sch_Save);
        root = findViewById(R.id.root_sch_set);

        textViews.add((TextView) findViewById(R.id.sch_from1));
        textViews.add((TextView) findViewById(R.id.sch_from2));
        textViews.add((TextView) findViewById(R.id.sch_from3));
        textViews.add((TextView) findViewById(R.id.sch_from4));
        textViews.add((TextView) findViewById(R.id.sch_from5));
        textViews.add((TextView) findViewById(R.id.sch_from6));
        textViews.add((TextView) findViewById(R.id.sch_from7));
        textViews.add((TextView) findViewById(R.id.sch_to1));
        textViews.add((TextView) findViewById(R.id.sch_to2));
        textViews.add((TextView) findViewById(R.id.sch_to3));
        textViews.add((TextView) findViewById(R.id.sch_to4));
        textViews.add((TextView) findViewById(R.id.sch_to5));
        textViews.add((TextView) findViewById(R.id.sch_to6));
        textViews.add((TextView) findViewById(R.id.sch_to7));

        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable1));
        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable2));
        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable3));
        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable4));
        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable5));
        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable6));
        checkBoxes.add((CheckBox) findViewById(R.id.sch_enable7));

        //dateFrom = findViewById(R.id.sch_data_from);
       // dateTo = findViewById(R.id.sch_data_to);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            int i = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.child("schedule").getChildren())
                {
                    Schedule sch = ds.getValue(Schedule.class);
                    assert sch != null;
                    textViews.get(i).setText(timeUnParse(sch.getTime_start()));
                    textViews.get(i+7).setText(timeUnParse(sch.getTime_finish()));
                    checkBoxes.get(i).setChecked(sch.isEnable());
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //onClickDate(dateFrom);
        //onClickDate(dateTo);

        for (TextView v : textViews) {
            onClickTime(v);
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime(textViews, checkBoxes);
                mDatabase.child("schedule")
                        .setValue(schedule).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Snackbar.make(root, "Расписание добавлено", Snackbar.LENGTH_SHORT)
                                .show();
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
                                if (minute < 10) {
                                    textViewTimeParam = hourOfDay + ":0" + minute;
                                } else {
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

    private void setTime(ArrayList<TextView> v, ArrayList<CheckBox> c) {
        for (int i = 0; i <= 6; i++) {

            String tempStart = v.get(i).getText().toString();
            String tempStop = v.get(i + 7).getText().toString();

            int timeStart = timeParse(tempStart); //+ (24 * 60 * i);
            int timeStop = timeParse(tempStop);// + (24 * 60 * i);

            schedule.add(new Schedule(timeStart, timeStop, c.get(i).isChecked()));
        }
    }

    private int timeParse(String temp) {
        int time = 0;
        if (temp.indexOf(':') == 1) {
            time = Integer.parseInt(temp.substring(0, 1)) * 60;
            time = time + Integer.parseInt(temp.substring(2, 4));
        } else if (temp.indexOf(':') == 2) {
            time = Integer.parseInt(temp.substring(0, 2)) * 60;
            time = time + Integer.parseInt(temp.substring(3, 5));
        }
        return time;
    }

    private String timeUnParse(int temp) {
        int minute = 0;
        int hours = 0;

        String sminute;
        String shours;

        hours = temp / 60;
        minute = temp - (hours * 60);

        if (minute == 0) {
            sminute = "00";
        } else if (minute < 10) {
            sminute = "0" + String.valueOf(minute);
        } else {
            sminute = String.valueOf(minute);
        }

        if (hours == 0) {
            shours = "00";
        } else if (hours < 10) {
            shours = "0" + String.valueOf(hours);
        } else {
            shours = String.valueOf(hours);
        }

        return shours+":"+sminute;
    }
}