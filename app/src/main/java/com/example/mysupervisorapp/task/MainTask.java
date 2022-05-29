package com.example.mysupervisorapp.task;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysupervisorapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainTask extends AppCompatActivity {
    RecyclerView taskRecycler;
    TextView addTask;
    TaskAdapter taskAdapter;
    ImageView noDataImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task);

        taskRecycler=findViewById(R.id.taskRecycler);
        taskRecycler.setLayoutManager(new LinearLayoutManager(this));
        addTask=findViewById(R.id.addTask);
        noDataImage=findViewById(R.id.noDataImage);


       // Glide.with(getApplicationContext()).load(R.drawable.first_note).into(noDataImage);



        FirebaseRecyclerOptions<TaskHelperClass> options =
                new FirebaseRecyclerOptions.Builder<TaskHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("task"), TaskHelperClass.class)
                        .build();





        taskAdapter = new TaskAdapter(options);


        taskRecycler.setAdapter(taskAdapter);

        //Toast.makeText(MainTask.this, "firebase"+taskRecycler, Toast.LENGTH_SHORT).show();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTask(View view){

        Intent intent=new Intent(MainTask.this, AddTask.class);
        startActivity(intent);




    }
}