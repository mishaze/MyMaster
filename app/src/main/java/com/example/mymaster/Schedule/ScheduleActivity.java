package com.example.mymaster.Schedule;

import android.content.Intent;
import android.os.Bundle;

import com.example.mymaster.Models.Clients;
import com.example.mymaster.Models.RecordingSession;
import com.example.mymaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ScheduleActivity extends AppCompatActivity {
    private final List<RecordingSession> items = new ArrayList<>();
    String name;
    static int j = 0;
    private final RecyclerView.Adapter adapter = new ScheduleAdapter(this.items);
    DatabaseReference rsDatabase;
    DatabaseReference cDatabase;
    List<RecordingSession> rs = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_main);
        rsDatabase = FirebaseDatabase.getInstance().getReference("Recording_Session");
        cDatabase = FirebaseDatabase.getInstance().getReference("Clients");


        Query rsQuery = rsDatabase.orderByChild("id_master").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        rsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    rs.add(ds.getValue(RecordingSession.class));


                    Query cQuery = cDatabase.orderByChild("uid").equalTo(rs.get(i).getId_client());
                    cQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            for (DataSnapshot ds : snapshot.getChildren()) {

                                Clients cl = ds.getValue(Clients.class);

                                name = cl.getFirst_name();
                                if (j >= rs.size()) j = 0;
                                rs.get(j).setId_client(name);

                                items.add(rs.get(j));
                                adapter.notifyDataSetChanged();
                                j++;
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        RecyclerView recyclerView = findViewById(R.id.sch_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        adapter.notifyItemInserted(this.items.size() - 1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ScheduleActivity.this, ScheduleSettingActivity.class));
            }
        });


    }

    private final static class ScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<RecordingSession> items;

        public ScheduleAdapter(List<RecordingSession> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.schedule_lisr_item, parent, false)
            ) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView name = holder.itemView.findViewById(R.id.sch_item_name);
            TextView service = holder.itemView.findViewById(R.id.sch_item_services);
            TextView time_start = holder.itemView.findViewById(R.id.sch_item_time_start);
            TextView time_end = holder.itemView.findViewById(R.id.sch_item_time_end);
            TextView date = holder.itemView.findViewById(R.id.sch_item_date);

            name.setText(this.items.get(position).getId_client());
            service.setText(this.items.get(position).getService());
            time_start.setText(timeUnParse(this.items.get(position).getStart_service()));
            time_end.setText(timeUnParse(this.items.get(position).getEnd_service()));
            date.setText(this.items.get(position).getDate());
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }




    static private String timeUnParse(String tempstr) {
       int temp = Integer.parseInt(tempstr);

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

    //TODO получение из базы данных списка записей

}