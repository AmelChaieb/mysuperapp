package com.example.mysupervisorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;

public class AtelierSearchAdapter extends FirebaseRecyclerAdapter<AtelierHelperClass,AtelierSearchAdapter.myViewwHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AtelierSearchAdapter(@NonNull FirebaseRecyclerOptions<AtelierHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AtelierSearchAdapter.myViewwHolder holder, int position, @NonNull AtelierHelperClass model) {

        holder.nomAte.setText(model.getNomA());
        holder.nomChefAe.setText(model.getNom_chef_atelier());
        holder.nbEqui.setText(model.getNombre_equipes());
        holder.nbMachine.setText(model.getNombre_machines());
        Glide.with(holder.imagee.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.imagee);

    }

    @NonNull
    @Override
    public AtelierSearchAdapter.myViewwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_at,parent ,false);
        return new AtelierSearchAdapter.myViewwHolder(view);
    }


    class myViewwHolder extends RecyclerView.ViewHolder {
        TextView nomAte, nomChefAe, nbEqui, nbMachine;
        RoundedImageView imagee;

        public myViewwHolder(@NonNull View itemView) {
            super(itemView);

            nomAte=itemView.findViewById(R.id.nomAtRech_txt);
            nomChefAe=itemView.findViewById(R.id.nomchefAtRech_txt);
            nbEqui=itemView.findViewById(R.id.nbrEqRech_txt);
            nbMachine=itemView.findViewById(R.id.nbrMaRech_txt);
            imagee=itemView.findViewById(R.id.featured_imgg);
        }
    }
}
