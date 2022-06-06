package com.example.mysupervisorapp;

import static android.net.Uri.*;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.BreakIterator;
import java.text.MessageFormat;
import java.util.Objects;

import javax.xml.namespace.QName;

import task.MainTask;

public class ChefAdapter extends FirebaseRecyclerAdapter<ChefAtelierHelper,ChefAdapter.myViewwHolder> {
    Context context;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ChefAdapter(@NonNull FirebaseRecyclerOptions<ChefAtelierHelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewwHolder holder, int position, @NonNull ChefAtelierHelper chefAtelierHelper) {

        holder.name.setText(chefAtelierHelper.getName());
        holder.phoneNo.setText(chefAtelierHelper.getPhoneNum());
        holder.nomAtelier.setText(chefAtelierHelper.getNomAtelier());
        Glide.with(holder.image.getContext())
                .load(chefAtelierHelper.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.image);


        holder.call.setOnClickListener(new View.OnClickListener() {
            final String mobilePhone= chefAtelierHelper.getPhoneNum();
            final String  appel= MessageFormat.format("tel:{0}", mobilePhone.trim());
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", appel, null));
                v.getContext().startActivity(intent);


            }


        });




    }


    @NonNull
    @Override
    public myViewwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent ,false);
        return new ChefAdapter.myViewwHolder(view);
    }

    class myViewwHolder extends RecyclerView.ViewHolder {
        TextView name, phoneNo, nomAtelier;
        Button call;
        RoundedImageView image;

        public myViewwHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.nomChef_txt);
            phoneNo=itemView.findViewById(R.id.phoneNumb_txt);
            nomAtelier=itemView.findViewById(R.id.atelierName_txt);
            call=itemView.findViewById(R.id.appler);
            image=itemView.findViewById(R.id.featured_img);
        }
    }
}
