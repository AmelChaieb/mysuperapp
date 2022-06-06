package com.example.mysupervisorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import searchAdmin.SearchAdmin;

public class AddAteliers extends AppCompatActivity {

    TextInputLayout nomAteliers, nomChefAtelier, nbEquipe, nbMachine ,surl_at;
    Button  addBtn;
    ImageButton backBtn;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ateliers);


        nomAteliers=findViewById(R.id.reg_Atelier);
        nomChefAtelier=findViewById(R.id.reg_nomChefAt);
        nbEquipe=findViewById(R.id.reg_nbgrp);
        nbMachine=findViewById(R.id.reg_nbMachineAt);
        surl_at=findViewById(R.id.reg_surlAt);
        addBtn=findViewById(R.id.reg_Ajbtn);
        backBtn=findViewById(R.id.arrowBtnAt);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddAteliers.this, AteliersList.class);
                startActivity(intent);
            }
        });


    }

    public void AjouterAt(View view) {
        reference= FirebaseDatabase.getInstance().getReference().child("ateliers");


        String nom_Ateliers = Objects.requireNonNull(nomAteliers.getEditText()).getText().toString();
        String nom_chef_at = Objects.requireNonNull(nomChefAtelier.getEditText()).getText().toString();
        String nb_Equipes = Objects.requireNonNull(nbEquipe.getEditText()).getText().toString();
        String nb_Machine = Objects.requireNonNull(nbMachine.getEditText()).getText().toString();
        String surl=Objects.requireNonNull(nbMachine.getEditText()).getText().toString();


        AtelierHelperClass atelierHelperClass=new AtelierHelperClass(nom_Ateliers,nom_chef_at,nb_Equipes,nb_Machine,surl);

        String id_atelier=reference.push().getKey();
        reference.child(id_atelier).setValue(atelierHelperClass);



        //show success message then finish the activity
        Toast.makeText(AddAteliers.this, "l'ajout fais avec succés", Toast.LENGTH_SHORT).show();




    }
}