package com.example.mysupervisorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class AteliersList extends AppCompatActivity {
    RecyclerView recyclerAtelier;
    AtelierAdapter atelierAdapter;
    ImageButton back;
    FloatingActionButton floatingActionButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ateliers_list);


        recyclerAtelier= (RecyclerView)findViewById(R.id.atelier_rcv);
        back=findViewById(R.id.arrowBtnnAt);
        recyclerAtelier.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<AtelierModel> options =
                new FirebaseRecyclerOptions.Builder<AtelierModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ateliers"), AtelierModel.class)
                        .build();


        atelierAdapter = new AtelierAdapter(options);
        recyclerAtelier.setAdapter(atelierAdapter);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButtonAt);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddAteliers.class));
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        atelierAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        atelierAdapter.stopListening();
    }
}