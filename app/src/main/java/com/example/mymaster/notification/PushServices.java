package com.example.mymaster.notification;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;



public class PushServices extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
