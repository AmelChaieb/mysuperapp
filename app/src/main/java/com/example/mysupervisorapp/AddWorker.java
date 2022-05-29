package com.example.mysupervisorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddWorker extends AppCompatActivity {
    //variables
    TextInputLayout regName, regStatut, regEmail, regPhoneNo, regPassword;
    Button regBtn;
    ImageButton backBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);

        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regStatut = findViewById(R.id.reg_statut);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);

        backBtn=findViewById(R.id.arrowBtn);

       backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddWorker.this, UsersList.class);
                startActivity(intent);

            }
        });




        }


        private Boolean validateName(){
            String val =regName.getEditText().getText().toString();
            if(val.isEmpty()){
                regName.setError("ce champs ne peut pas etre vide !");
                return false;
            }
            else{
                regName.setError(null);
                return true;
            }


    }

       private Boolean validateStatut(){

        String val = regStatut.getEditText().getText().toString();

        if(val.isEmpty()){
            regName.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else if(val.length()>=15){
            regName.setError("le statut est si long");
            return false;
        }


        else{
            regName.setError(null);
            regStatut.setErrorEnabled(false);
            return true;
        }


    }

        private Boolean validateEmail() {
          String val = regEmail.getEditText().getText().toString();
          String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

          if (val.isEmpty()) {
              regEmail.setError("Champs ne peut pas etre vide");
              return false;
          } else if (!val.matches(emailPattern)) {
              regEmail.setError("Adresse email invalide");
              return false;
          } else {
              regEmail.setError(null);
              regEmail.setErrorEnabled(false);
              return true;
          }
    }

        private Boolean validatePhoneNo() {
           String val = regPhoneNo.getEditText().getText().toString();



           if (val.isEmpty()) {
              regPhoneNo.setError("Champs ne peut pas etre vide");
              return false;
           }
           else {
              regPhoneNo.setError(null);
              regPhoneNo.setErrorEnabled(false);
              return true;
           }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();


        if (val.isEmpty()) {
            regPassword.setError("Champs ne peut pas etre vide");
            return false;
        } //else if (!val.matches(passwordVal)) {
            //regPassword.setError("Mot de passe ne doit pas comporter des caractère spéciaux!");
            //return false;
       // } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    //}




    //save data in firebase
    public void registerUser(View View)  {


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateStatut())
        {
            return;
        }


        //get all the values
        String name =regName.getEditText().getText().toString();
        String statut = regStatut.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();



            UserHelperClass helperClass = new UserHelperClass(name, statut, email, phoneNo, password);
            reference.child(password).setValue(helperClass);
            //show success message then finish the activity
            Toast.makeText(AddWorker.this, "l'ajout fais avec succés", Toast.LENGTH_SHORT).show();



        }
    }
