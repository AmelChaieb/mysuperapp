package com.example.mysupervisorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddMachine extends AppCompatActivity {

    TextInputLayout   regNbHeure, regTmpPause, regTmpRemplissage;

    Button regBtnAdd;
    ImageButton backBtn;
    Spinner spinnerEtat, spinnerPanne ;

    DatabaseReference reference;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_machine);

        //Hooks to all xml elements in activity_sign_up.xml
       // regMachine = findViewById(R.id.reg_Machine);

        regNbHeure = findViewById(R.id.reg_nbHeure);
        regTmpPause = findViewById(R.id.reg_pause);
        regTmpRemplissage = findViewById(R.id.reg_remplissage);
        spinnerEtat=findViewById(R.id.spin_1);
        spinnerPanne=findViewById(R.id.spin_2);
        regBtnAdd=findViewById(R.id.reg_btnA);



        backBtn=findViewById(R.id.arrowBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddMachine.this, MachineList.class);
                startActivity(intent);

            }
        });


    }



    /*private Boolean validateNbHeure(){
        String val = Objects.requireNonNull(regNbHeure.getEditText()).getText().toString();
        if(val.isEmpty()){
            regNbHeure.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regNbHeure.setError(null);
            return true;
        }


    }*/

    /*private Boolean validatePause(){
        String val = Objects.requireNonNull(regTmpPause.getEditText()).getText().toString();
        if(val.isEmpty()){
            regTmpPause.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regTmpPause.setError(null);
            return true;
        }


    }*/


   /* private Boolean validateRemplissage(){
        String val = regTmpRemplissage.getEditText().getText().toString();
        if(val.isEmpty()){
            regTmpRemplissage.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regTmpRemplissage.setError(null);
            return true;
        }


    }*/



    public void Ajout(View View)  {

        reference=FirebaseDatabase.getInstance().getReference().child("machine");

        //get all the values

        String nbrHeure = Objects.requireNonNull(regNbHeure.getEditText()).getText().toString();
        String temps_pause = Objects.requireNonNull(regTmpPause.getEditText()).getText().toString();
        String temps_remplissage = Objects.requireNonNull(regTmpRemplissage.getEditText()).getText().toString();
        String etat_de_fonct=spinnerEtat.getSelectedItem().toString();

        String defPanne = spinnerPanne.getSelectedItem().toString();


        //MachineHelperClasse helperClass = new MachineHelperClasse(defPanne, lastUserMod,nbrHeure, temps_pause, temps_remplissage);
        MachineHelperClasse helperClass= new MachineHelperClasse(nbrHeure, temps_pause, temps_remplissage, etat_de_fonct, defPanne);

        String id_machine=reference.push().getKey();
        reference.child(id_machine).setValue(helperClass);



        //show success message then finish the activity
        Toast.makeText(AddMachine.this, "l'ajout fais avec succés", Toast.LENGTH_SHORT).show();



    }
}


