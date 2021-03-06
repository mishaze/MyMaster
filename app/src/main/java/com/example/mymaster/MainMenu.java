package com.example.mymaster;

import android.content.Intent;
import android.os.Bundle;

import com.example.mymaster.Friend.FriendActivity;
import com.example.mymaster.Myprofile.MyProfile;
import com.example.mymaster.Schedule.ScheduleActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


public class MainMenu extends AppCompatActivity {
    Button btnSchedule, btnClients, btnMyProfile, btnSetting, btnHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSchedule = findViewById(R.id.btnScheduler);
        btnClients = findViewById(R.id.btnClients);
        btnMyProfile = findViewById(R.id.btnMyProfil);
        btnSetting = findViewById(R.id.btnSetting);
        btnHelp = findViewById(R.id.btnHelp);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    return;
                }
                // Get new FCM registration token
                String token = task.getResult();
                // Log and toast

                Toast.makeText(MainMenu.this, token, Toast.LENGTH_SHORT).show();
                Log.d("TAG",token);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, ScheduleActivity.class));
            }
        });

        btnClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, FriendActivity.class));
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, SettingsActivity.class));
            }
        });

        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, MyProfile.class));
            }
        });


    }
}