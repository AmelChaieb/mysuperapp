package task;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mysupervisorapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@RequiresApi(api = Build.VERSION_CODES.N)
public class AddTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText regtitle, regdescription, regnomAtelier, regnomGroup , regnomChefEquipe, regdate;
    ImageButton backBtn;
    Button dateBtnn;

    DatabaseReference reference;
    Calendar calendar = Calendar.getInstance();
    Locale id = new Locale("fr", "ID");
    Date datee;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy", id);
    Context context;
    AlertDialog builderAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        regtitle=findViewById(R.id.addTaskTitle);
        regdescription=findViewById(R.id.addTaskDescription);
        regnomAtelier=findViewById(R.id.taskAtelier);
        regnomGroup=findViewById(R.id.taskGroup);
        regnomChefEquipe=findViewById(R.id.taskCE);
        dateBtnn=findViewById(R.id.dateBtn);

        regdate=findViewById(R.id.taskDate);
        backBtn=findViewById(R.id.arrowBtnTask);

       backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddTask.this, MainTask.class);
                startActivity(intent);

            }
        });

        dateBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });











    }



    private Boolean validateTitre(){
        String val =regtitle.getText().toString();
        if(val.isEmpty()){
            regtitle.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regtitle.setError(null);
            return true;
        }


    }

    private Boolean validateDescription(){
        String val =regdescription.getText().toString();
        if(val.isEmpty()){
            regdescription.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regdescription.setError(null);
            return true;
        }


    }

    private Boolean validateNomAtelier(){
        String val =regnomAtelier.getText().toString();
        if(val.isEmpty()){
            regnomAtelier.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regnomAtelier.setError(null);
            return true;
        }


    }


    private Boolean validateNomCE(){
        String val =regnomChefEquipe.getText().toString();
        if(val.isEmpty()){
            regnomChefEquipe.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regnomChefEquipe.setError(null);
            return true;
        }


    }

    private Boolean validateGroup(){
        String val =regnomGroup.getText().toString();
        if(val.isEmpty()){
            regnomGroup.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regnomGroup.setError(null);
            return true;
        }


    }


    private Boolean validateDate(){
        String val =regdate.getText().toString();
        if(val.isEmpty()){
            regdate.setError("ce champs ne peut pas etre vide !");
            return false;
        }
        else{
            regdate.setError(null);
            return true;
        }


    }


    public void Ajouter(View view){


        reference= FirebaseDatabase.getInstance().getReference().child("task");
        if(!validateTitre() |!validateDescription() | !validateNomAtelier() | !validateNomCE() | !validateGroup()
        | !validateDate())
        {
            return;
        }

        //get all the values
        String title=regtitle.getText().toString();
        String description=regdescription.getText().toString();
        String nomAtelier=regnomAtelier.getText().toString();
        String nomGroup=regnomGroup.getText().toString();
        String nomChefEquipe=regnomChefEquipe.getText().toString();
        String date=regdate.getText().toString();


        TaskModelClass taskModelClass=new TaskModelClass(title,description,nomAtelier,nomGroup,nomChefEquipe,date);
        String id_task=reference.push().getKey();
        reference.child(id_task).setValue(taskModelClass);

        //String id_machine=reference.push().getKey();
        //        reference.child(id_machine).setValue(helperClass);
        Toast.makeText(AddTask.this, "l'ajout fais avec succés", Toast.LENGTH_SHORT).show();



    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        calendar.set(year, month, dayOfMonth);
        regdate.setText(simpleDateFormat.format(calendar.getTime()));
        datee = calendar.getTime();



    }
}