package com.example.mysupervisorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;

public class AtelierSearchMachineAdapter extends FirebaseRecyclerAdapter<MachineHelperClasse,AtelierSearchMachineAdapter.myViewwHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AtelierSearchMachineAdapter(@NonNull FirebaseRecyclerOptions<MachineHelperClasse> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull AtelierSearchMachineAdapter.myViewwHolder holder, int position, @NonNull MachineHelperClasse model) {

        holder.etatdef.setText(model.getEtat_de_fonct());
        holder.nomatelier.setText(model.getAtelier());
        holder.typepanne.setText(model.getDefPanne());
        holder.nbheuremarche.setText(model.getNbrHeure());
        holder.tmppause.setText(model.getTemps_pause());
        holder.tmprempli.setText(model.getTemps_remplissage());
        holder.uselastmod.setText(model.getLastUserMod());


        Glide.with(holder.image.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.image);

    }

    @NonNull
    @Override
    public AtelierSearchMachineAdapter.myViewwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_machine_search,parent ,false);
        return new AtelierSearchMachineAdapter.myViewwHolder(view);
    }

    class myViewwHolder extends RecyclerView.ViewHolder {
        TextView etatdef, nomatelier, typepanne, nbheuremarche,tmppause,tmprempli,uselastmod;
        RoundedImageView image;

        public myViewwHolder(@NonNull View itemView) {
            super(itemView);

            etatdef=itemView.findViewById(R.id.etatDeFoncMach);
            nomatelier=itemView.findViewById(R.id.nomAtMach);
            typepanne=itemView.findViewById(R.id.defPanneMach);
            nbheuremarche=itemView.findViewById(R.id.nbrHeureMach);
            tmppause=itemView.findViewById(R.id.temps_pauseMach);
            tmprempli=itemView.findViewById(R.id.temps_remplissageMach);
            uselastmod=itemView.findViewById(R.id.lastUserModMach);
            image=itemView.findViewById(R.id.featured_imggg);
        }
    }
}
