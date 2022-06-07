package searchAdmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysupervisorapp.AddAteliers;
import com.example.mysupervisorapp.AtelierHelperClass;
import com.example.mysupervisorapp.AtelierSearchAdapter;
import com.example.mysupervisorapp.AtelierSearchMachineAdapter;
import com.example.mysupervisorapp.EmployerSearchAdapter;
import com.example.mysupervisorapp.MachineAdapter;
import com.example.mysupervisorapp.MachineHelperClasse;
import com.example.mysupervisorapp.MachineModel;
import com.example.mysupervisorapp.MainActivity;
import com.example.mysupervisorapp.R;
import com.example.mysupervisorapp.TechnicienMaintAdapter;
import com.example.mysupervisorapp.TechnicienMaintenanceHelper;
import com.example.mysupervisorapp.UserAdapter;
import com.example.mysupervisorapp.UserHelperClass;
import com.example.mysupervisorapp.UserModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Locale;

public class SearchAdmin extends AppCompatActivity {
    EditText search;
    ImageButton searchBtn;
    RecyclerView resultList,recyclerView, userRecycler;
    EmployerSearchAdapter employerSearchAdapter;
    FirebaseDatabase database;
    DatabaseReference databaseReferenceAt, databaseReferenceMa,databaseReferenceEp;
    UserAdapter userAdapter;

    RadioButton nomAtelier,nomMachine, nomEmploye;
    AtelierSearchAdapter atelierSearchAdapter;
    TextView ajouterUA;
    AtelierSearchMachineAdapter atelierSearchMachineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_admin_page);

        database=FirebaseDatabase.getInstance();
        databaseReferenceAt=database.getReference("ateliers");
        databaseReferenceMa=database.getReference("machine");
        databaseReferenceEp=database.getReference("users");
        nomAtelier=findViewById(R.id.rdate);
        nomEmploye=findViewById(R.id.rdEmp);
        nomMachine=findViewById(R.id.rdMach);
        searchBtn=findViewById(R.id.search_btn);
        search=findViewById(R.id.search_filed);





        recyclerView =findViewById(R.id.resultListRecyclerMa);
        userRecycler=findViewById(R.id.resultListRecyclerUt);
        resultList=findViewById(R.id.resultListRecyclerAt);

        ajouterUA=findViewById(R.id.ajouterAt);
        ajouterUA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchAdmin.this, AddAteliers.class);
                startActivity(intent);
            }
        });





      searchBtn.setOnClickListener(new View.OnClickListener() {

          String searchText=search.getText().toString();
          @Override
          public void onClick(View v) {

              search(searchText);
              Toast.makeText(SearchAdmin.this, searchText+"", Toast.LENGTH_SHORT).show();

          }
      });



        atelierListResult();
        machineListResult();
        userListResult();






    }

    private void search( String searchText) {

       // userRecycler.setHasFixedSize(true);
       // userRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
       // String query=search.getText().toString().toUpperCase();
       // Query search=databaseReferenceEp.orderByChild("name").startAt(query).endAt(query+"\uf0ff");

        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("name").startAt(searchText).endAt(searchText + "~"), UserHelperClass.class)
                        .build();

        employerSearchAdapter = new EmployerSearchAdapter(options);
        userRecycler.setAdapter(employerSearchAdapter);
        employerSearchAdapter.startListening();

    }

    private void userListResult() {
       // userRecycler.setLayoutManager(new LinearLayoutManager(this));
        userRecycler.setHasFixedSize(true);
       userRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<UserHelperClass> options =
                new FirebaseRecyclerOptions.Builder<UserHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), UserHelperClass.class)
                        .build();

        employerSearchAdapter = new EmployerSearchAdapter(options);
        userRecycler.setAdapter(employerSearchAdapter);

    }

    private void machineListResult() {

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<MachineHelperClasse> options =
                new FirebaseRecyclerOptions.Builder<MachineHelperClasse>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("machine"), MachineHelperClasse.class)
                        .build();


        atelierSearchMachineAdapter = new AtelierSearchMachineAdapter(options);
        recyclerView.setAdapter(atelierSearchMachineAdapter);
    }

    private void atelierListResult() {

        resultList.setHasFixedSize(true);
        resultList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<AtelierHelperClass> options =
                new FirebaseRecyclerOptions.Builder<AtelierHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ateliers"), AtelierHelperClass.class)
                        .build();


        atelierSearchAdapter = new AtelierSearchAdapter(options);
        resultList.setAdapter(atelierSearchAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        atelierSearchAdapter.startListening();
        atelierSearchMachineAdapter.startListening();
        employerSearchAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();

        atelierSearchAdapter.stopListening();
        atelierSearchMachineAdapter.stopListening();
        employerSearchAdapter.stopListening();
    }
}