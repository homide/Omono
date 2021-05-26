package com.project.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

public class Main2Activity extends AppCompatActivity implements arraySave, NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MyAdapterGeneral adapter;
    boolean condition = false;
    int initialSize;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout = findViewById(R.id.drawer_layout3);
        navigationView = findViewById(R.id.navView3);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.navigation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        int catSelector = getIntent().getIntExtra("cat", 0);
        switch(catSelector){
            case 0:
                initialSize = arraySave.products.size();
                adapter = new MyAdapterGeneral(Main2Activity.this, arraySave.products);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                recyclerView.setAdapter(adapter);
                break;
            case 1:
                MyAdaptorFashion adaptorfashion = new MyAdaptorFashion(Main2Activity.this, arraySave.products);
                recyclerView.setLayoutManager(new GridLayoutManager(Main2Activity.this, 2));
                recyclerView.setAdapter(adaptorfashion);
                break;
            case 2:
                MyAdaptorGroceries adaptorgroceries = new MyAdaptorGroceries(Main2Activity.this, arraySave.products);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                recyclerView.setAdapter(adaptorgroceries);
                break;
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!condition) {
                    if (initialSize < arraySave.products.size()) {
                        adapter.notifyDataSetChanged();
                        initialSize = arraySave.products.size();
                        handler.postDelayed(this, 1000);
                    }else{
                        handler.postDelayed(this, 1000);
                    }
                }
                else {
                    condition = true;
                }
            }
        }, 2000);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            arraySave.products.clear();
            Intent intent = new Intent(this, SelectCategoryClass.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Toast.makeText(this, "This doesn't have a funtion yet", Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}