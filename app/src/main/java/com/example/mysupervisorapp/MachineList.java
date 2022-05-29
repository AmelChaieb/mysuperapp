package com.example.mysupervisorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MachineList extends AppCompatActivity {

    RecyclerView recyclerView;
    MachineAdapter machineAdapter;
    FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macchine_list);

        recyclerView = findViewById(R.id.rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<MachineModel> options =
                new FirebaseRecyclerOptions.Builder<MachineModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("machine"), MachineModel.class)
                        .build();


        machineAdapter = new MachineAdapter(options);
        recyclerView.setAdapter(machineAdapter);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddMachine.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        machineAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        machineAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {

        FirebaseRecyclerOptions<MachineModel> options =
                new FirebaseRecyclerOptions.Builder<MachineModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("machine").orderByChild("id_machine").startAt(str).endAt(str + "~"), MachineModel.class)
                        .build();

        machineAdapter = new MachineAdapter(options);
        machineAdapter.startListening();
        recyclerView.setAdapter(machineAdapter);

    }
}

