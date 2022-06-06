package com.example.mysupervisorapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedImageView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AtelierAdapter extends FirebaseRecyclerAdapter<AtelierModel,AtelierAdapter.myViewwHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AtelierAdapter(@NonNull FirebaseRecyclerOptions<AtelierModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AtelierAdapter.myViewwHolder holder, int position, @NonNull AtelierModel model) {

        holder.nomAt.setText(model.getNomA());
        holder.nomChefAt.setText(model.getNom_chef_atelier());
        holder.nbEq.setText(model.getNombre_equipes());
        holder.nbMachine.setText(model.getNombre_machines());


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.image.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup4))
                        .setExpanded(true, 1800)
                        .create();
                //dialogPlus.show();
                View view = dialogPlus.getHolderView();





                EditText nom_at = view.findViewById(R.id.txtNomAt);
                EditText nom_che_at = view.findViewById(R.id.txtNomChefAt);
                EditText nb_eq = view.findViewById(R.id.txtNbEqAt);
                EditText nb_machine = view.findViewById(R.id.txtNbMaAt);
                EditText surl=view.findViewById(R.id.txtsurImg);




                Button btnUpdate = view.findViewById(R.id.BtnUpdateMaint);


                nom_at.setText(model.getNomA());
                nom_che_at.setText(model.getNom_chef_atelier());
                nb_eq.setText(model.getNombre_equipes());
                nb_machine.setText(model.getNombre_machines());
                surl.setText(model.getSurl());


                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        Map<String, Object> map = new HashMap<>();


                        map.put("nomA", nom_at.getText().toString());
                        map.put("nom_che_atelier", nom_che_at.getText().toString());
                        map.put("nombre_equipe", nb_eq.getText().toString());
                        map.put("nombre_machine", nb_machine.getText().toString());
                        map.put("surl", surl.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("ateliers")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nomAt.getContext(), "Données modifier avec succés", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.nomAt.getContext(), "Erreur lors du modification", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });


                    }
                });


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nomAt.getContext());
                builder.setTitle(" vous-êtes sûre?");
                builder.setMessage("Cet donnée ne peut pas être récupérer.");

                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("machine")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(holder.nomAt.getContext(), "Annulé.", Toast.LENGTH_SHORT).show();


                    }
                });

                builder.show();
            }
        });




    }

    @NonNull
    @Override
    public AtelierAdapter.myViewwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.atelier_item,parent ,false);
        return new AtelierAdapter.myViewwHolder(view);
    }


    class myViewwHolder extends RecyclerView.ViewHolder {
        TextView nomAt, nomChefAt, nbEq ,nbMachine;
        CircleImageView image;
        Button btnEdit, btnDelete;



        public myViewwHolder(@NonNull View itemView) {
            super(itemView);

            nomAt=itemView.findViewById(R.id.nomAat_txt);
            nomChefAt=itemView.findViewById(R.id.nomChefAt_txt);
            nbEq=itemView.findViewById(R.id.nbEqAt_txt);
            nbMachine=itemView.findViewById(R.id.nbMachineAt_txt);
            image=itemView.findViewById(R.id.img6);
            btnEdit=itemView.findViewById(R.id.editBtnAt);
            btnDelete=itemView.findViewById(R.id.deleteBtnAt);

        }
    }
}
