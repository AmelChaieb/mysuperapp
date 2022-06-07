package task;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysupervisorapp.R;
import com.example.mysupervisorapp.SessionManager;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainTask extends AppCompatActivity {
    RecyclerView taskRecycler;
    TextView addTask;
    TaskAdapter taskAdapter;
    ImageView noDataImage;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_task);

        taskRecycler=findViewById(R.id.taskRecycler);
        addTask=findViewById(R.id.addTask);
        noDataImage=findViewById(R.id.noDataImage);


        // SessionManager sessionManager=new SessionManager(UpdateProfile.this,SessionManager.SESSION_USERSESSION);
        //        HashMap<String,String> userDetails =sessionManager.getUserDetailFromSession();
        //
        //
        //
        //        //Toast.makeText(UpdateProfile.this, fullname+"\n"+phoneNo, Toast.LENGTH_SHORT).show();
        //        textView.setText(userDetails.get(SessionManager.KEY_FULLNAME));


       // Glide.with(getApplicationContext()).load(R.drawable.first_note).into(noDataImage);

        SessionManager sessionManager=new SessionManager(MainTask.this,SessionManager.SSESSIONN_USERSESSION);
        HashMap<String,String> userDetails =sessionManager.getUserDetailFromSession();



        taskRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        String numGroup=userDetails.get(SessionManager.KEY_NOMCHEFEQUIPE);
        Toast.makeText(MainTask.this,numGroup+"",Toast.LENGTH_LONG).show();

        FirebaseRecyclerOptions<TaskHelperClass> options =
                new FirebaseRecyclerOptions.Builder<TaskHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("task").orderByChild("nomChefEquipe").equalTo(numGroup), TaskHelperClass.class)
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