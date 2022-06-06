package com.example.mysupervisorapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
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

public class ChefEquipeAdapter extends FirebaseRecyclerAdapter<ChefEquipeHelper,ChefEquipeAdapter.myViewHolder> {

    private Admin context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ChefEquipeAdapter(@NonNull FirebaseRecyclerOptions<ChefEquipeHelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ChefEquipeHelper chefEquipeHelper) {

        holder.nomChefEq.setText(chefEquipeHelper.getName());
        holder.mobile.setText(chefEquipeHelper.getPhoneNo());
        holder.nomAte.setText(chefEquipeHelper.getNomAtelier());
        Glide.with(holder.imagee.getContext())
                .load(chefEquipeHelper.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.imagee);

        holder.appelE.setOnClickListener(new View.OnClickListener() {

            String mobilePhone= chefEquipeHelper.getPhoneNo();
            String  appel="tel:"+mobilePhone.trim();
            @Override
            public void onClick(View v) {



                open301(v);
            }

            public void open301(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(appel));
                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_equipe_card,parent ,false);
        return new ChefEquipeAdapter.myViewHolder(view);
    }


    class myViewHolder extends RecyclerView.ViewHolder {

        TextView nomChefEq,nomAte,mobile;
        Button appelE;
        RoundedImageView imagee;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);



            nomChefEq=itemView.findViewById(R.id.nomChefEquipe_txt);
            nomAte=itemView.findViewById(R.id.atelierNameE_txt);
            mobile=itemView.findViewById(R.id.phoneNumbE_txt);
            appelE=itemView.findViewById(R.id.applerE);
            imagee=itemView.findViewById(R.id.featured_imge);

        }
    }
}
