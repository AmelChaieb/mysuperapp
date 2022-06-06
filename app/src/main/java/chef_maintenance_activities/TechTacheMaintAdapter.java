package chef_maintenance_activities;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysupervisorapp.MachineAdapter;
import com.example.mysupervisorapp.R;
import com.example.mysupervisorapp.TechnicienMaintAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import task.TaskAdapter;
import task.TaskHelperClass;
@RequiresApi(api = Build.VERSION_CODES.N)
public class TechTacheMaintAdapter extends FirebaseRecyclerAdapter<TechTacheMaintHelper, TechTacheMaintAdapter.myViewHolder> {
    Locale id = new Locale("fr", "ID");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy", id);
    PopupMenu popupMenu;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TechTacheMaintAdapter(@NonNull FirebaseRecyclerOptions<TechTacheMaintHelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TechTacheMaintHelper model) {
        holder.compName.setText(model.getCompleter_par());
        holder.nomAt.setText(model.getNomAtelier());
        holder.descrMaint.setText(model.getTextTache());
        holder.warning.setText(model.getWarningtxt());
        holder.optionsMaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = com.orhanobut.dialogplus.DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.popup_3_maint))
                        .setExpanded(true, 1200)
                        .create();
                //dialogPlus.show();
                View view = dialogPlus.getHolderView();
                EditText compPar = view.findViewById(R.id.txtNAmeDooer);
                TextView warning=view.findViewById(R.id.warning);
                Spinner warningsp=view.findViewById(R.id.spin_5);

                Button btnUpdate = view.findViewById(R.id.BtnUpdateMaint);


                compPar.setText(model.getCompleter_par());
                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        warningsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 1: warning.setTextColor(Color.GREEN);
                                    break;
                                    case 2: warning.setTextColor(Color.RED);
                                    break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                        String warningg = warningsp.getSelectedItem().toString();
                        Map<String, Object> map = new HashMap<>();
                        map.put("completer_par", compPar.getText().toString());
                        map.put("warningtxt", warningg);

                        FirebaseDatabase.getInstance().getReference().child("tacheMaint")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.compName.getContext(), "Données modifier avec succés", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.compName.getContext(), "Erreur lors du modification", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });
                    }
                });

            }
        });



        try {
            String date = model.getDate();
            String[] items1 = date.split("-");
            String day = items1[0];
            String year = items1[1];
            String month = items1[2];

            holder.day.setText(day);
            holder.year.setText(year);
            holder.month.setText(month);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_maintenance,parent ,false);
        return new TechTacheMaintAdapter.myViewHolder(view);



    }




   static class myViewHolder extends RecyclerView.ViewHolder {
       TextView nomAt, descrMaint, compName, year,day,month,warning;
       ImageView optionsMaint,img;

       public myViewHolder(@NonNull View itemView) {
           super(itemView);
           nomAt = itemView.findViewById(R.id.nomAtMaint);
           descrMaint= itemView.findViewById(R.id.descripTacheMaint);
           compName = itemView.findViewById(R.id.completedBy);
           year = itemView.findViewById(R.id.yearMaint);
           day=itemView.findViewById(R.id.dayMaint);
           month=itemView.findViewById(R.id.monthMaint);
           warning=itemView.findViewById(R.id.warning);
           img=itemView.findViewById(R.id.img5);
           optionsMaint=itemView.findViewById(R.id.optionsMaint);

       }
   }
}
