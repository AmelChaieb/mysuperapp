package com.example.mysupervisorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateProfile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        TextView textView=findViewById(R.id.textV);


        SessionManager sessionManager=new SessionManager(UpdateProfile.this,SessionManager.SESSION_USERSESSION);
        HashMap<String,String> userDetails =sessionManager.getUserDetailFromSession();



        //Toast.makeText(UpdateProfile.this, fullname+"\n"+phoneNo, Toast.LENGTH_SHORT).show();
        textView.setText(userDetails.get(SessionManager.KEY_FULLNAME));
        //+(userDetails.get(SessionManager.KEY_PHONE)));


    }
}