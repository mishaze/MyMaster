package com.example.mymaster;

import android.os.Bundle;

import com.example.mymaster.Models.Clients;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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

public class FriendActivity extends AppCompatActivity {
    private final List<Clients> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new FriendActivity.FriendAdapter(this.items);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        RecyclerView recyclerView = findViewById(R.id.friend_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        this.items.add(new Clients("Misha","Zusman","misha@mish","88945654564"));
        adapter.notifyItemInserted(this.items.size()-1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private final static class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Clients> items;

        public FriendAdapter(List<Clients> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.friend_item, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView first_name = holder.itemView.findViewById(R.id.fri_item_first_name);
            first_name.setText(this.items.get(position).getFirst_name());
            TextView second_name = holder.itemView.findViewById(R.id.fri_item_second_name);
            second_name.setText(this.items.get(position).getSecond_name());
            TextView phone = holder.itemView.findViewById(R.id.fri_item_phone);
            phone.setText(this.items.get(position).getPhone());
            TextView email = holder.itemView.findViewById(R.id.fri_item_email);
            email.setText(this.items.get(position).getEmail());
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}