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

import java.text.MessageFormat;

public class EmployerSearchAdapter extends FirebaseRecyclerAdapter<UserHelperClass, EmployerSearchAdapter.myViewwHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EmployerSearchAdapter(@NonNull FirebaseRecyclerOptions<UserHelperClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewwHolder holder, int position, @NonNull UserHelperClass model) {

        holder.nbEq.setText(model.getNumGroupe());
        holder.nomemploye.setText(model.getName());
        holder.nomAtelier.setText(model.getNomAtelier());
        holder.nomChef.setText(model.getNomChefEquipe());
        holder.phoneNum.setText(model.getPhoneNo());
        holder.cin.setText(model.getPassword());
        holder.statut.setText(model.getStatut());


        Glide.with(holder.imag.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark).
                circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal).
                into(holder.imag);
        holder.empCall.setOnClickListener(new View.OnClickListener() {
            final String mobilePhone= model.getPhoneNo();
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
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_data_search,parent ,false);
        return new EmployerSearchAdapter.myViewwHolder(view);
    }



public class myViewwHolder extends RecyclerView.ViewHolder {
        TextView nomemploye, nomAtelier, nbEq, nomChef,phoneNum, statut,cin;
        RoundedImageView imag;
        Button empCall;


        public myViewwHolder(@NonNull View itemView) {
            super(itemView);

            nomemploye=itemView.findViewById(R.id.empName);
            nomAtelier=itemView.findViewById(R.id.nomAtemp);
            nbEq=itemView.findViewById(R.id.numGrpEmp);
            nomChef=itemView.findViewById(R.id.nomchefEqEmp);
            phoneNum=itemView.findViewById(R.id.phoneNumEmp);
            statut=itemView.findViewById(R.id.statutEmp);
            cin=itemView.findViewById(R.id.cinUt_txt);

            imag=itemView.findViewById(R.id.featured_imgee);
            empCall=itemView.findViewById(R.id.applerEmp);
        }
    }
}
