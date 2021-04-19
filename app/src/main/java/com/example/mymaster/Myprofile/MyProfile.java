package com.example.mymaster.Myprofile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mymaster.R;
import com.example.mymaster.ServicesListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;


public class MyProfile extends AppCompatActivity {
    EditText firstName, secondName, phone, email, address,info;
    Button services, save;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        mDatabase = FirebaseDatabase.getInstance().getReference("Masters").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());

        firstName = findViewById(R.id.mpFirstname);
        secondName = findViewById(R.id.mpName);
        phone = findViewById(R.id.mpPhone);
        email = findViewById(R.id.mpEmail);
        email.setEnabled(false);
        address = findViewById(R.id.mpAddress);
        info = findViewById(R.id.mpInfo);

        services = findViewById(R.id.mp_btn_services);
        save = findViewById(R.id.mp_btn_save);

        viewUserInfo();

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile.this, ServicesListActivity.class));

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstName.getText().toString().length()>1) {
                    mDatabase.child("first_name")
                            .setValue(firstName.getText().toString());
                } else {
                    Toast.makeText(MyProfile.this,"Не верно введено имя",Toast.LENGTH_LONG).show();
                }

                mDatabase.child("second_name")
                        .setValue(secondName.getText().toString());


                mDatabase.child("phone")
                        .setValue(phone.getText().toString());

                mDatabase.child("address")
                        .setValue(address.getText().toString());


                mDatabase.child("info")
                        .setValue(info.getText().toString());


            }
        });


    }

    private void viewUserInfo(){
        mDatabase.child("first_name")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    firstName.setText(task.getResult()
                            .getValue()
                            .toString());
                }
            }
        });

        mDatabase.child("second_name")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    secondName.setText(task
                            .getResult()
                            .getValue()
                            .toString());
                }
            }
        });

        mDatabase.child("phone")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    phone.setText(task
                            .getResult()
                            .getValue()
                            .toString());
                }
            }
        });

        mDatabase.child("address")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    address.setText(task
                            .getResult()
                            .getValue()
                            .toString());
                }
            }
        });

        mDatabase.child("info")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    info.setText(task
                            .getResult()
                            .getValue()
                            .toString());
                }
            }
        });

        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

}