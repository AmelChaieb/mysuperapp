package task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
        addTask=findViewById(R.id.addTask);
        noDataImage=findViewById(R.id.noDataImage);


       // Glide.with(getApplicationContext()).load(R.drawable.first_note).into(noDataImage);

        taskRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        FirebaseRecyclerOptions<TaskHelperClass> options =
                new FirebaseRecyclerOptions.Builder<TaskHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("task"), TaskHelperClass.class)
                        .build();





        taskAdapter = new TaskAdapter(options);

        //Toast.makeText(MainTask.this, "firebase"+taskAdapter, Toast.LENGTH_SHORT).show();
        taskRecycler.setAdapter(taskAdapter);



    }

    public void addTask(View view){

        Intent intent=new Intent(MainTask.this, AddTask.class);
        startActivity(intent);




    }

    @Override
    protected void onStart() {
        super.onStart();
        taskAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        taskAdapter.stopListening();
    }
}