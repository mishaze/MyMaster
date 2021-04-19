package com.example.mymaster;

import android.os.Bundle;

import com.example.mymaster.Models.ScheduleItem;
import com.example.mymaster.Models.Services;
import com.example.mymaster.Myprofile.ServicesActivity;
import com.example.mymaster.Schedule.ScheduleActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServicesListActivity extends AppCompatActivity {

    private final List<Services> items = new ArrayList<>();
    private final List<Services> services = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new ServicesListActivity.ServicesAdapter(this.items);
    private static final List<EditText> names= new ArrayList<>();
    private static final List<EditText> times = new ArrayList<>();
    private static final List<EditText> prices = new ArrayList<>();
    DatabaseReference mDatabase;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_list);
        mDatabase = FirebaseDatabase.getInstance().getReference("Masters").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("list_services");

        RecyclerView recyclerView = findViewById(R.id.ser_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        save = findViewById(R.id.ser_btn_save);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (items.size()>14)  return;
                    items.add(new Services());
                    adapter.notifyItemInserted(items.size() - 1);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setServices();
                mDatabase.setValue(services);
                services.clear();
            }
        });

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    items.add(ds.getValue(Services.class));
                    adapter.notifyItemInserted(items.size()-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setServices() {
        for(int i=0; i<items.size();i++)
        {
            if(names.get(i).getText().toString().equals("")&&times.get(i).getText().toString().equals(""))
                continue;
            services.add(new Services(names.get(i).getText().toString(),prices.get(i).getText().toString(),times.get(i).getText().toString()));
        }
    }


    private final static class ServicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Services> items;

        public ServicesAdapter(List<Services> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_service, parent, false)
            ) {};
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            TextView num =  holder.itemView.findViewById(R.id.ser_num);
            EditText name = holder.itemView.findViewById(R.id.ser_name);
            EditText price = holder.itemView.findViewById(R.id.ser_price);
            EditText time = holder.itemView.findViewById(R.id.ser_time);

            num.setText(String.valueOf(position+1));
            name.setText(this.items.get(position).getName());
            price.setText(this.items.get(position).getPrice());
            time.setText(this.items.get(position).getTime());

            names.add(name);
            prices.add(price);
            times.add(time);
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }

}

