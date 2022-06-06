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

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MachineAdapter extends FirebaseRecyclerAdapter<MachineModel,MachineAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MachineAdapter(@NonNull FirebaseRecyclerOptions<MachineModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MachineModel model) {


        holder.etatMachine.setText(model.getEtat_de_fonct());
        holder.nbrHeure.setText(model.getNbrHeure());
        holder.lastUserMod.setText(model.getLastUserMod());
        holder.tmpPause.setText(model.getTemps_pause());
        holder.tmpRemplissage.setText(model.getTemps_remplissage());
        holder.typePanne.setText(model.getDefPanne());
        holder.atelier.setText(model.getAtelier());
        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.img);




        //Glide.with(holder.img.getContext())
        //      .load(model.getSu)


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup_2))
                        .setExpanded(true, 2000)
                        .create();
                //dialogPlus.show();
                View view = dialogPlus.getHolderView();




                Spinner spinnerPane=view.findViewById(R.id.spin_4);
                Spinner spinnerEtt=view.findViewById(R.id.spin_3);
                EditText temps_pause = view.findViewById(R.id.txtPause);
                EditText nbrHeure = view.findViewById(R.id.txtnbHeure);
                EditText temps_remplissage = view.findViewById(R.id.txtRemplissage);
                EditText lastUserMod = view.findViewById(R.id.txtModif);
                EditText nameAt = view.findViewById(R.id.nameat);




                Button btnUpdate = view.findViewById(R.id.BtnUpdate);


                temps_pause.setText(model.getTemps_pause());
                temps_remplissage.setText(model.getTemps_remplissage());
                nbrHeure.setText(model.getNbrHeure());
                lastUserMod.setText(model.getLastUserMod());
                nameAt.setText(model.getAtelier());


                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String defPanne = spinnerPane.getSelectedItem().toString();
                        String etat_de_fonct=spinnerEtt.getSelectedItem().toString();

                        Map<String, Object> map = new HashMap<>();


                        map.put("etat_de_fonct", etat_de_fonct);
                        map.put("defPanne", defPanne);
                        map.put("temps_pause", temps_pause.getText().toString());
                        map.put("temps_remplissage", temps_remplissage.getText().toString());
                        map.put("lastUserMod", lastUserMod.getText().toString());
                        map.put("nbrHeure", nbrHeure.getText().toString());
                        map.put("atelier", nameAt.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("machine")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nbrHeure.getContext(), "Données modifier avec succés", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.nbrHeure.getContext(), "Erreur lors du modification", Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nbrHeure.getContext());
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

                        Toast.makeText(holder.nbrHeure.getContext(), "Annulé.", Toast.LENGTH_SHORT).show();


                    }
                });

                builder.show();
            }
        });


    }







    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.machine_list_item,parent ,false);
        return new MachineAdapter.myViewHolder(view);
    }

     class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView  etatMachine, typePanne,nbrHeure ,tmpPause, tmpRemplissage, lastUserMod,atelier,idMachine;
        Button btnEdit, btnDelete;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img= itemView.findViewById(R.id.img2);

            etatMachine= itemView.findViewById(R.id.etatf_txt);
            typePanne= itemView.findViewById(R.id.defPanne_txt);
            nbrHeure= itemView.findViewById(R.id.nbHeure_txt);
            tmpPause= itemView.findViewById(R.id.tempsDePause_txt);
            tmpRemplissage= itemView.findViewById(R.id.tempsRemp_txt);
            lastUserMod= itemView.findViewById(R.id.userLastMod_txt);
            atelier=itemView.findViewById(R.id.nomAte_txt);
            idMachine=itemView.findViewById(R.id.idMach_txt);


            btnEdit= itemView.findViewById(R.id.editBtn);
            btnDelete= itemView.findViewById(R.id.deleteBtn);

        }
    }
}
