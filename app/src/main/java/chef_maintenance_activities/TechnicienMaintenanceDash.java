package chef_maintenance_activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mysupervisorapp.Login;
import com.example.mysupervisorapp.MachineAdapter;
import com.example.mysupervisorapp.MachineModel;
import com.example.mysupervisorapp.R;
import com.example.mysupervisorapp.SessionManager;
import com.example.mysupervisorapp.UpdateProfile;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class TechnicienMaintenanceDash extends AppCompatActivity {

    RecyclerView maintaskRecycler;
    TechTacheMaintAdapter techTacheMaintAdapter;
    TextView nomUt;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicien_maintenance_dash);
        nomUt=findViewById(R.id.nom_user);


        SessionManager sessionManager=new SessionManager(TechnicienMaintenanceDash.this,SessionManager.SSESSIONN_USERSESSION);
        HashMap<String,String> userDetails =sessionManager.getUserDetailFromSession();



        //Toast.makeText(UpdateProfile.this, fullname+"\n"+phoneNo, Toast.LENGTH_SHORT).show();
        nomUt.setText(userDetails.get(SessionManager.KEY_FULLNAME));
        //+(userDetails.get(SessionManager.KEY_PHONE)));


        maintaskRecycler=findViewById(R.id.mainttaskRecycler);

        maintaskRecycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<TechTacheMaintHelper> options =
                new FirebaseRecyclerOptions.Builder<TechTacheMaintHelper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("tacheMaint"), TechTacheMaintHelper.class)
                        .build();



        techTacheMaintAdapter = new TechTacheMaintAdapter(options);
        maintaskRecycler.setAdapter(techTacheMaintAdapter);

        ImageButton logoutbtn;
        logoutbtn = findViewById(R.id.logoutMaint);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("UserPref", AppCompatActivity.MODE_PRIVATE).edit().clear().apply();
                //finish()
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });








    }


    @Override
    protected void onStart() {
        super.onStart();
        techTacheMaintAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        techTacheMaintAdapter.stopListening();
    }
}