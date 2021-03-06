package com.example.mysupervisorapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;


import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends FirebaseRecyclerAdapter<UserModel,UserAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirebaseRecyclerOptions<UserModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull UserModel model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.statut.setText(model.getStatut());

        //Glide.with(holder.img.getContext())
          //      .load(model.getSu)






        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                       .setContentHolder(new ViewHolder(R.layout.update_popup))
                       .setExpanded(true,1800)
                       .create();
               //dialogPlus.show();
                View view=dialogPlus.getHolderView();
                EditText name=view.findViewById(R.id.txtName);
                EditText email=view.findViewById(R.id.txtEmail);
                EditText phone=view.findViewById(R.id.txtPhoneNo);
                EditText statut=view.findViewById(R.id.txtStatut);
                EditText password=view.findViewById(R.id.txtPassword);
               // EditText Surl=view.findViewById(R.id.txtImageUrl);

                Button btnUpdate=view.findViewById(R.id.BtnUpdate);

                name.setText(model.getName());
                email.setText(model.getEmail());
                statut.setText(model.getStatut());
                phone.setText(model.getPhoneNo());
                password.setText(model.getPassword());

                //Surl.setText(model.getSurl());
                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map= new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("email", email.getText().toString());
                        map.put("statut", statut.getText().toString());
                        map.put("password", password.getText().toString());
                        map.put("phoneNo", phone.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(getRef(holder.getAdapterPosition()).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(),"Donn??es modifier avec succ??s",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.name.getContext(),"Erreur lors du modification",Toast.LENGTH_SHORT).show();
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
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle(" vous-??tes s??re?");
                builder.setMessage("Cet donn??e ne peut pas ??tre r??cup??rer.");

                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(holder.name.getContext(),"Annul??.",Toast.LENGTH_SHORT).show();


                    }
                });

                builder.show();
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item,parent ,false);
        return new myViewHolder(view);


    }

    class myViewHolder extends RecyclerView.ViewHolder{


        CircleImageView img;
        TextView name, statut, email;

        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img=(CircleImageView)itemView.findViewById(R.id.img1);
            name=(TextView)itemView.findViewById(R.id.name_txt);
            statut=(TextView)itemView.findViewById(R.id.statut_txt);
            email=(TextView)itemView.findViewById(R.id.email_txt);

            btnEdit=(Button)itemView.findViewById(R.id.editBtn);
            btnDelete=(Button)itemView.findViewById(R.id.deleteBtn);



        }
    }

}
