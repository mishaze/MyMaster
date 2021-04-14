package com.example.mymaster;

import android.content.Intent;
import android.os.Bundle;

import com.example.mymaster.Models.ScheduleItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private final List<ScheduleItem> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ScheduleAdapter(this.items);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_main);

        this.items.add(new ScheduleItem("Миша Зусман"));
        RecyclerView recyclerView = findViewById(R.id.sch_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        this.items.add(new ScheduleItem("Миша Зусман"));
        adapter.notifyItemInserted(this.items.size()-1);

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
        private final List<ScheduleItem> items;

        public ScheduleAdapter(List<ScheduleItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.schedule_lisr_item, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView name = holder.itemView.findViewById(R.id.sch_item_name);
            name.setText(String.format("%s. %s", position, this.items.get(position).getName()));
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }


    //TODO получение из базы списка записей

}