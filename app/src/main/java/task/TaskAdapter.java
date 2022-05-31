package task;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysupervisorapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class TaskAdapter extends FirebaseRecyclerAdapter<TaskHelperClass,TaskAdapter.myViewHolder>  {
    Locale id = new Locale("fr", "ID");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy", id);
    Date date = null;
    String outputDateString = null;
     private MainTask context;
    PopupMenu popupMenu;
    private int position;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TaskAdapter(@NonNull FirebaseRecyclerOptions<TaskHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TaskHelperClass taskHelperClass) {

        holder.title.setText(taskHelperClass.getTitle());
        holder.description.setText(taskHelperClass.getDescription());
        holder.nomAtelier.setText(taskHelperClass.getNomAtelier());
        holder.nomGroup.setText(taskHelperClass.getNomGroup());
        holder.nomChefEquipe.setText(taskHelperClass.getNomChefEquipe());

       // holder.status.setText(taskHelperClass.isComplete() ? "COMPLETED" : "UPCOMING");


        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu = new PopupMenu(v.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                       if (item.getItemId()==R.id.menuDelete){
                           AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                            builder.setTitle(R.string.delete_confirmation).setMessage(R.string.sureToDelete).
                                    setPositiveButton(R.string.yes, (dialog, which) -> {

                                        FirebaseDatabase.getInstance().getReference().child("task")
                                                .child(Objects.requireNonNull(getRef(holder.getAdapterPosition()).getKey())).removeValue();


                                    })
                                    .setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel()).show();

                        }

                        if(item.getItemId() == R.id.menuUpdate){

                            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                                    .setContentHolder(new ViewHolder(R.layout.fragment_create_task))
                                    .setExpanded(true, 2000)
                                    .create();
                            //dialogPlus.show();
                            View view = dialogPlus.getHolderView();

                            EditText _titre=view.findViewById(R.id.modiftaskTitle);
                            EditText _description=view.findViewById(R.id.modifTaskDescription);
                            EditText _nomGroup=view.findViewById(R.id.modifTaskGroup);
                            EditText _nomAtelier=view.findViewById(R.id.modifTaskAtelier);
                            EditText _nomChefEquipe=view.findViewById(R.id.modifTaskCE);
                            EditText _dateMod=view.findViewById(R.id.modifTaskDate);

                            Button _date=view.findViewById(R.id.dateBtnModif);

                            Button _updateTask=view.findViewById(R.id.updateTask);

                           _titre.setText(taskHelperClass.getTitle());
                           _description.setText(taskHelperClass.getDescription());
                           _nomGroup.setText(taskHelperClass.getNomGroup());
                           _nomAtelier.setText(taskHelperClass.getNomAtelier());
                           _nomChefEquipe.setText(taskHelperClass.getNomChefEquipe());



                           dialogPlus.show();

                           _updateTask.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View v) {


                                   Map<String, Object> map = new HashMap<>();

                                   map.put("date", _date.getText().toString());
                                   map.put("description", _description.getText().toString());
                                   map.put("nomAtelier", _nomAtelier.getText().toString());
                                   map.put("nomChefEquipe", _nomChefEquipe.getText().toString());
                                   map.put("nomGroup", _nomGroup.getText().toString());
                                   map.put("title", _titre.getText().toString());

                                   FirebaseDatabase.getInstance().getReference().child("task")
                                           .child(getRef(holder.getAdapterPosition()).getKey())
                                           .updateChildren(map)
                                           .addOnSuccessListener(new OnSuccessListener<Void>() {
                                               @Override
                                               public void onSuccess(Void unused) {
                                                   Toast.makeText(holder.title.getContext(), "Données modifier avec succés", Toast.LENGTH_SHORT).show();
                                                   dialogPlus.dismiss();
                                               }
                                           })
                                           .addOnFailureListener(new OnFailureListener() {
                                               @Override
                                               public void onFailure(@NonNull Exception e) {
                                                   Toast.makeText(holder.title.getContext(), "Erreur lors du modification", Toast.LENGTH_SHORT).show();
                                                   dialogPlus.dismiss();

                                               }
                                           });

                               }
                           });








                        }
                        if (item.getItemId()==R.id.menuComplete){}
                        return true;
                    }
                });
                popupMenu.show();



            }
        });


        try {
            String date = taskHelperClass.getDate();
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent ,false);
        return new myViewHolder(view);

    }



    class myViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, nomAtelier, nomGroup, nomChefEquipe, month, day,year,  status;
        ImageView options,img;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titre);
            description = itemView.findViewById(R.id.description);
            nomAtelier = itemView.findViewById(R.id.atelier);
            nomGroup = itemView.findViewById(R.id.group);
            nomChefEquipe = itemView.findViewById(R.id.achefEq);
            status = itemView.findViewById(R.id.status);
            year = itemView.findViewById(R.id.year);
            day=itemView.findViewById(R.id.day);
            month=itemView.findViewById(R.id.month);
            img=itemView.findViewById(R.id.img4);
            options=itemView.findViewById(R.id.options);





        }
    }


















}