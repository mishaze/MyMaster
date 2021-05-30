package com.example.mymaster.Friend;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymaster.Models.Clients;
import com.example.mymaster.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AddFriend extends AppCompatActivity {

    TextView uid;
    EditText email;
    Button addFriend;

    private DatabaseReference mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        uid = findViewById(R.id.Uid);
        email = findViewById(R.id.Uid_friend);
        addFriend = findViewById(R.id.btn_add_friend);

        mDb = FirebaseDatabase.getInstance().getReference("Masters").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("friends");
        uid.setText("Введите Email пользователя");

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().equals(""))
                    return;

               loadData();
            }
        });

    }

    private void loadData () {

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Clients");
        Query dataQuery = dbRef.orderByChild("email").equalTo(email.getText().toString());

        dataQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren())
                {
                    Clients clients = ds.getValue(Clients.class);
                    mDb.push().setValue(clients.getUid());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
}