package com.project.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class searchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView2;
    ImageView generalCat, groceriesCat, fashionCat, medCat, electroCat;
    DrawerLayout drawerLayout;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        generalCat = findViewById(R.id.generalCat);
        groceriesCat = findViewById(R.id.groceriesCat);
        fashionCat = findViewById(R.id.fashionCat);
        medCat = findViewById(R.id.medCat);
        electroCat = findViewById(R.id.electroCat);
        back_btn = findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchActivity.super.onBackPressed();
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView2 = findViewById(R.id.navView2);
        navigationView2.bringToFront();
        navigationView2.setNavigationItemSelectedListener(this);

        generalCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(searchActivity.this, General_category.class);
                startActivity(cinemaIntent);
            }
        });

        groceriesCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(searchActivity.this, Groceries_category.class);
                startActivity(cinemaIntent);
            }
        });

        fashionCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(searchActivity.this, Fashion_category.class);
                startActivity(cinemaIntent);
            }
        });

        medCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(searchActivity.this, Medicine_category.class);
                startActivity(cinemaIntent);
            }
        });

        electroCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(searchActivity.this, Electronics_category.class);
                startActivity(cinemaIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //test

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Toast.makeText(this, "This doesn't have a funtion yet", Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}