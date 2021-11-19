package com.example.carservice;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView=findViewById(R.id.navigatio);
        drawerLayout=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.Open_navigation, R.string.close_navigation);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent;
        if(item.getItemId()==R.id.home){
            intent=new Intent(getApplicationContext(), home.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.Addcar){
            setContentView(R.layout.addcar);
        }else if(item.getItemId()==R.id.myService){
            setContentView(R.layout.myservice);
        }else if (item.getItemId()==R.id.mechanic){
            setContentView(R.layout.mechanic);
        }else if (item.getItemId()==R.id.carExpenses){
            setContentView(R.layout.carexpenses);
        }else if (item.getItemId()==R.id.notification){
            setContentView(R.layout.notifications);
        }else  if (item.getItemId()==R.id.profile){
            setContentView(R.layout.profile);
        }


        return true;
    }
}