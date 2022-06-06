package com.example.mysupervisorapp;

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

public class TechnicienMaintAdapter extends FirebaseRecyclerAdapter<TechnicienMaintenanceHelper,TechnicienMaintAdapter.myViewHolder> {
    private Admin context;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TechnicienMaintAdapter(@NonNull FirebaseRecyclerOptions<TechnicienMaintenanceHelper> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TechnicienMaintenanceHelper maintenanceHelper) {

        holder.nomTech.setText(maintenanceHelper.getName());
        holder.mobile.setText(maintenanceHelper.getPhoneNum());

        Glide.with(holder.img.getContext())
                .load(maintenanceHelper.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.img);

        holder.appeler.setOnClickListener(new View.OnClickListener() {

            String mobilePhone= maintenanceHelper.getPhoneNum();
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.technicien_maint_card,parent ,false);
        return new TechnicienMaintAdapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder  {

        TextView nomTech,mobile;
        Button appeler;
        RoundedImageView img;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);



           nomTech=itemView.findViewById(R.id.nomTech_txt);
            mobile=itemView.findViewById(R.id.phoneTech_txt);
            appeler=itemView.findViewById(R.id.applerTech);
            img=itemView.findViewById(R.id.featured_imgg);

        }
    }
}
