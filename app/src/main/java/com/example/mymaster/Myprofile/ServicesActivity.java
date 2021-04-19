package com.example.mymaster.Myprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mymaster.Models.Services;
import com.example.mymaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Objects;

public class ServicesActivity extends AppCompatActivity {

    ArrayList<EditText> nameET = new ArrayList<>();
    ArrayList<EditText> priceET = new ArrayList<>();
    ArrayList<EditText> timeET = new ArrayList<>();
    ArrayList<Services> services = new ArrayList<>();

    DatabaseReference mDatabase;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        mDatabase = FirebaseDatabase.getInstance().getReference("Masters").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

        nameET.add((EditText) findViewById(R.id.ser_name1));
        nameET.add((EditText) findViewById(R.id.ser_name2));
        nameET.add((EditText) findViewById(R.id.ser_name3));
        nameET.add((EditText) findViewById(R.id.ser_name4));

        priceET.add((EditText) findViewById(R.id.ser_price1));
        priceET.add((EditText) findViewById(R.id.ser_price2));
        priceET.add((EditText) findViewById(R.id.ser_price3));
        priceET.add((EditText) findViewById(R.id.ser_price4));

        timeET.add((EditText) findViewById(R.id.ser_time1));
        timeET.add((EditText) findViewById(R.id.ser_time2));
        timeET.add((EditText) findViewById(R.id.ser_time3));
        timeET.add((EditText) findViewById(R.id.ser_time4));

        save = findViewById(R.id.ser_btn_save);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            final int i = 0;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.child("list_services").getChildren()) {
                    Services ser = ds.getValue(Services.class);

                    assert ser != null;
                    nameET.get(i).setText(ser.getName());
                    priceET.get(i).setText(ser.getPrice());
                    timeET.get(i).setText(ser.getTime());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setServices();
                if (services.isEmpty())
                    return;

                mDatabase.child("list_services").setValue(services).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ServicesActivity.this, "Данные сохранены", Toast.LENGTH_LONG).show();
                        services.clear();
                    }
                });
            }
        });

    }

    private void err() {
        for (int i = 0; i < 4; i++) {
            services.add(new Services("", "", ""));
        }

        mDatabase.child("list_services").setValue(services).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ServicesActivity.this, "Ошибка, обновите данные", Toast.LENGTH_LONG).show();
                services.clear();
            }
        });
    }

    private void setServices() {
        for (int i = 0; i < 4; i++) {

            services.add(new Services(nameET.get(i).getText().toString(), priceET.get(i).getText().toString(), timeET.get(i).getText().toString()));
        }
    }


    private void viewUserInf() {
        for (int i = 0; i < 4; i++) {
            final int finalI = i;

            mDatabase.child("list_services")
                    .child(String.valueOf(i))
                    .child("name")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(Task<DataSnapshot> task) {
                    if (task.getResult().getValue() == null) {
                        err();
                        return;
                    }
                    nameET.get(finalI).setText(task.getResult().getValue().toString());
                }

            });

            mDatabase.child("list_services")
                    .child(String.valueOf(i))
                    .child("time")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(Task<DataSnapshot> task) {

                    timeET.get(finalI).setText(task.getResult().getValue().toString());
                }
            });

            mDatabase.child("list_services")
                    .child(String.valueOf(i))
                    .child("price")
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(Task<DataSnapshot> task) {
                    priceET.get(finalI).setText(task.getResult().getValue().toString());
                }
            });
        }
    }
}