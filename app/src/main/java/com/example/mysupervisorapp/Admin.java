package com.example.mysupervisorapp;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import androidx.appcompat.widget.SearchView;

public class Admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener   {

   RecyclerView recyclerChefEq, featuredRecycler, techRecycler;

   ChefAdapter chefAdapter;
   ChefEquipeAdapter chefEquipeAdapter;
   TechnicienMaintAdapter technicienMaintAdapter;
   EditText search;
   ImageButton adRech;


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
        search=findViewById(R.id.rech_admin);
        adRech=findViewById(R.id.rech_admin_btn);


       // adminSearch=findViewById(R.id.adminSearch);
        //adminSearch.clearFocus();
       /* adminSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearchTech(query);
                txtSearchfeat(query);
                txtSearchEq(query);
                return false;
            }
*/


           /* @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }

            private void txtSearchTech(String str) {
                techRecycler.setHasFixedSize(true);
                techRecycler.setLayoutManager(new LinearLayoutManager(Admin.this, LinearLayoutManager.HORIZONTAL, false));

                FirebaseRecyclerOptions<TechnicienMaintenanceHelper> options =
                        new FirebaseRecyclerOptions.Builder<TechnicienMaintenanceHelper>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("techniciensMaint").orderByChild("name").startAt(str).endAt(str + "~"), TechnicienMaintenanceHelper.class)
                                .build();

                technicienMaintAdapter = new TechnicienMaintAdapter(options);
                techRecycler.setAdapter(technicienMaintAdapter);
                technicienMaintAdapter.startListening();


            }

            private void txtSearchfeat(String str) {
                featuredRecycler.setHasFixedSize(true);
                featuredRecycler.setLayoutManager(new LinearLayoutManager(Admin.this, LinearLayoutManager.HORIZONTAL, false));

                FirebaseRecyclerOptions<ChefAtelierHelper> options =
                        new FirebaseRecyclerOptions.Builder<ChefAtelierHelper>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("chefAteliers").orderByChild("name").startAt(str).endAt(str + "~"), ChefAtelierHelper.class)
                                .build();

                chefAdapter = new ChefAdapter(options);
                featuredRecycler.setAdapter(chefAdapter);
                chefAdapter.startListening();


            }


            private void txtSearchEq(String str) {
                recyclerChefEq.setHasFixedSize(true);
                recyclerChefEq.setLayoutManager(new LinearLayoutManager(Admin.this, LinearLayoutManager.HORIZONTAL, false));

                FirebaseRecyclerOptions<ChefEquipeHelper> options =
                        new FirebaseRecyclerOptions.Builder<ChefEquipeHelper>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("chefAteliers").orderByChild("name").startAt(str).endAt(str + "~"), ChefEquipeHelper.class)
                                .build();

                chefEquipeAdapter = new ChefEquipeAdapter(options);
                recyclerChefEq.setAdapter(chefEquipeAdapter);
                chefEquipeAdapter.startListening();


            }
        });

*/
        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);


        naviagtionDrawer();

        //hooks
        featuredRecycler=findViewById(R.id.featured_recycler);
        recyclerChefEq=findViewById(R.id.recyclerChefEquipe);
        techRecycler=findViewById(R.id.techRecycler);

        featuredRecycler();
        chefEquipeRecycler();
        techRecycler();


        }

    private void techRecycler() {
        techRecycler.setHasFixedSize(true);
        techRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<TechnicienMaintenanceHelper> options =
                new FirebaseRecyclerOptions.Builder<TechnicienMaintenanceHelper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("techniciensMaint"), TechnicienMaintenanceHelper.class)
                        .build();


        technicienMaintAdapter = new TechnicienMaintAdapter(options);
        techRecycler.setAdapter(technicienMaintAdapter);


    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<ChefAtelierHelper> options =
                new FirebaseRecyclerOptions.Builder<ChefAtelierHelper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("chefAteliers"), ChefAtelierHelper.class)
                        .build();

        chefAdapter = new ChefAdapter(options);
        featuredRecycler.setAdapter(chefAdapter);

    }


    @Override
    protected void onStart() {
        super.onStart();
        chefAdapter.startListening();
        chefEquipeAdapter.startListening();
        technicienMaintAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        chefAdapter.stopListening();
        chefEquipeAdapter.stopListening();
        technicienMaintAdapter.stopListening();
    }

    //Chef Equipe recycler

    private void chefEquipeRecycler() {
        recyclerChefEq.setHasFixedSize(true);
        recyclerChefEq.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<ChefEquipeHelper> options =
                new FirebaseRecyclerOptions.Builder<ChefEquipeHelper>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("chefEquipes"), ChefEquipeHelper.class)
                        .build();



        chefEquipeAdapter = new ChefEquipeAdapter(options);
        recyclerChefEq.setAdapter(chefEquipeAdapter);



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
