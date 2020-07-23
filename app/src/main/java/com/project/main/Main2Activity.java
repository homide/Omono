//Refer to MainActivity for Commenting

package com.project.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;


public class Main2Activity extends AppCompatActivity implements arraySave, NavigationView.OnNavigationItemSelectedListener {
    //test
    public RelativeLayout searchbar;
    DrawerLayout drawerLayout3;
    NavigationView navigationView3;
//    Toolbar toolbar3;
//    TextView resultText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout3 = findViewById(R.id.drawer_layout3);
//        toolbar3 = findViewById(R.id.toolbar);
        navigationView3 = findViewById(R.id.navView3);
        navigationView3.bringToFront();
//        setSupportActionBar(toolbar3);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout3, toolbar3, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
//        drawerLayout3.addDrawerListener(toggle);
//        toggle.syncState();
        navigationView3.setNavigationItemSelectedListener(this);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdaptor adaptor = new MyAdaptor(Main2Activity.this, arraySave.products);
        recyclerView.setLayoutManager(new GridLayoutManager(Main2Activity.this, 2));
        recyclerView.setAdapter(adaptor);


        searchbar = (RelativeLayout) findViewById(R.id.buttonBar);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, searchActivity.class);
                startActivity(intent);
            }
        });
        }
    @Override
    protected void onStart () {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState (@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onRestart () {
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause () {
        super.onPause();
    }

    @Override
    protected void onPostResume () {
        super.onPostResume();
    }

    @Override
    protected void onStop () {
        super.onStop();
    }


    @Override
    public void onBackPressed () {
        if (drawerLayout3.isDrawerOpen(GravityCompat.START)) {
            drawerLayout3.closeDrawer(GravityCompat.START);
        } else {
            arraySave.products.clear();
            Intent intent = new Intent(this, searchActivity.class);
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