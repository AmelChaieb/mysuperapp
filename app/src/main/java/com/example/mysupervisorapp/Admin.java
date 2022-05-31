package com.example.mysupervisorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   RecyclerView recyclerView;
   UserAdapter userAdapter;
   RecyclerView featuredRecycler;


    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;
    static final float END_SCALE = 0.7f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_admin);

        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);


        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        naviagtionDrawer();

        //hooks







        //recyclerView= (RecyclerView)findViewById(R.id.rv);

      //  FirebaseRecyclerOptions<UserModel> options =
        //        new FirebaseRecyclerOptions.Builder<UserModel>()
          //              .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), UserModel.class)
            //            .build();

        //userAdapter = new UserAdapter(options);
        //recyclerView.setAdapter(userAdapter);

        }

    //Navigation Drawer Functions


    private void naviagtionDrawer() {

        //Naviagtion Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

       menuIcon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if (drawerLayout.isDrawerVisible(GravityCompat.START))
                   drawerLayout.closeDrawer(GravityCompat.START);
               else drawerLayout.openDrawer(GravityCompat.START);

           }



        });

       animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }
       else {
        super.onBackPressed();}




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }

    public void ClickAccueil(MenuItem item) {
        redirectActivity(this,Admin.class);
    }

    public void Search(MenuItem item) {
        redirectActivity(this,OuvrierDash.class);
    }

    private  static void redirectActivity(Activity activity, Class aClass) {
        Intent intent= new Intent(activity,aClass);
        activity.startActivity(intent);
    }



}
