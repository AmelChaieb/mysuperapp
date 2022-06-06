package com.example.mysupervisorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseArray;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;

import chef_maintenance_activities.TechnicienMaintenanceDash;

public class Login extends AppCompatActivity {
    Button loginBtn, forgetPass;
    TextView logoText, sloganText;
    TextInputLayout username, password;
    TextInputEditText usernameEditText, passwordEditText;


    CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        forgetPass = findViewById(R.id.forget_pass);
        usernameEditText=findViewById(R.id.login_user_name_editText);
        passwordEditText=findViewById(R.id.login_password_editText);
        rememberMe=findViewById(R.id.remember_me);

        // check if username and password are already saved in sharepreferences or not
        SessionManager sessionManager=new SessionManager(Login.this,SessionManager.SESSION_REMEMBERME);
        if(sessionManager.checkRememberMe()){
            HashMap<String,String> rememberMeDetails=sessionManager.getRemeberMeDetailsFromSession();
            usernameEditText.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONNAME));
            passwordEditText.setText(rememberMeDetails.get(SessionManager.KEY_SESSIONPASSWORD));

        }







    }


    private Boolean validateUsername() {
        String val = Objects.requireNonNull(username.getEditText()).getText().toString();

        if (val.isEmpty()) {
            username.setError("ce champs ne peut pas etre vide !");
            return false;
        } else {
            username.setError(null);
            return true;
        }


    }

    private Boolean validatePassword() {
        String val = Objects.requireNonNull(password.getEditText()).getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword()) {
            return;


        } else {
            isUser();

        }
    }


    private void isUser() {
        //progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
        final String userEnteredPassword = Objects.requireNonNull(password.getEditText()).getText().toString().trim();

        if(rememberMe.isChecked()){
            SessionManager sessionManager=new SessionManager(Login.this,SessionManager.SESSION_REMEMBERME);
            sessionManager.createRememberMeSession(userEnteredUsername,userEnteredPassword);
        }






        Query checkUser = FirebaseDatabase.getInstance().getReference("users").orderByChild("name").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredPassword).child("password").getValue(String.class);
                   String statutUser =snapshot.child(userEnteredPassword).child("statut").getValue(String.class);

                    if (passwordFromDB != null) {
                        if (passwordFromDB.equals(userEnteredPassword) && statutUser.equals("admin")) {
                            password.setError(null);
                            password.setErrorEnabled(false);




                                String _fullname = snapshot.child(userEnteredPassword).child("name").getValue(String.class);
                                String _email=snapshot.child(userEnteredPassword).child("email").getValue(String.class);
                                String _password=snapshot.child(userEnteredPassword).child("password").getValue(String.class);
                                String _phoneNo=snapshot.child(userEnteredPassword).child("phoneNo").getValue(String.class);
                                String _statut=snapshot.child(userEnteredPassword).child("statut").getValue(String.class);

                                SessionManager sessionManager=new SessionManager(Login.this,SessionManager.SSESSIONN_USERSESSION);
                                sessionManager.createLoginSession(_fullname);
                                //Intent intent = new Intent(Login.this, Admin.class);
                                startActivity(new Intent(getApplicationContext(),UpdateProfile.class));

                            Toast.makeText(Login.this, _fullname+"\n"+_email+"\n"+_phoneNo+"\n"+_statut, Toast.LENGTH_SHORT).show();






                        }
                        else {
                            password.setError(null);
                            password.setErrorEnabled(false);
                            Intent intent = new Intent(Login.this, TechnicienMaintenanceDash.class);
                            startActivity(intent);


                        }


                    } else {
                      //  progressBar.setVisibility(View.VISIBLE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }

                else{
                   // progressBar.setVisibility(View.VISIBLE);
                    username.setError("No such User exist");
                    username.requestFocus();

                    }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });



       }

}












