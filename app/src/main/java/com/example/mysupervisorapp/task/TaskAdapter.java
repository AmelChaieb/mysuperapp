package com.example.mysupervisorapp.task;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysupervisorapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class TaskAdapter extends FirebaseRecyclerAdapter<TaskHelperClass,TaskAdapter.myViewHolder> {
    Locale id = new Locale("fr", "ID");
    public SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd MMM yyyy", Locale.US);
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy", id);
    Date date = null;
    String outputDateString = null;
    private MainTask context;


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
        holder.options.setOnClickListener(view -> showPopUpMenu(view, position));

        try {
            date = simpleDateFormat.parse(taskHelperClass.getDate());
            outputDateString = dateFormat.format(date);

            String[] items1 = outputDateString.split(" ");
            String day = items1[0];
            String dd = items1[1];
            String month = items1[2];

            holder.day.setText(day);
            holder.date.setText(dd);
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
    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView title, description, nomAtelier, nomGroup, nomChefEquipe, date, month, day,  status;
        ImageView options;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titre);
            description = itemView.findViewById(R.id.description);
            nomAtelier = itemView.findViewById(R.id.atelier);
            nomGroup = itemView.findViewById(R.id.group);
            nomChefEquipe = itemView.findViewById(R.id.achefEq);
            status = itemView.findViewById(R.id.status);
            date = itemView.findViewById(R.id.date);
            month = itemView.findViewById(R.id.month);
            day = itemView.findViewById(R.id.day);
            options=itemView.findViewById(R.id.options);


        }
    }




    @SuppressLint("NonConstantResourceId")
    public void showPopUpMenu(View view, int position) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menuDelete) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme_Dialog);
                alertDialogBuilder.setTitle(R.string.delete_confirmation).setMessage(R.string.sureToDelete).
                        setPositiveButton(R.string.yes, (dialog, which) -> FirebaseDatabase.getInstance().getReference().child("task")
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue())
                        .setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel()).show();
            }
            return false;
        });
        popupMenu.show();
    }


}