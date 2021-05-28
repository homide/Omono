package com.project.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
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
    int initialSize;
    AutoCompleteTextView searchBar;
    Button search;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int catSelector = getIntent().getIntExtra("cat", 0);

        drawerLayout = findViewById(R.id.drawer_layout3);
        navigationView = findViewById(R.id.navView3);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        searchBar = findViewById(R.id.searchText);
        search = findViewById(R.id.btnSearch);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        search.setOnClickListener(v->{
            String searchtext = searchBar.getText().toString();
            if (searchtext.length() <= 0) {
                Toast.makeText(Main2Activity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
            } else {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                arraySave.products.clear();
                final ProgressDialog pd = new ProgressDialog(Main2Activity.this);
                pd.setMessage("Searching websites...");
                pd.show();
                pd.setCanceledOnTouchOutside(false);
                CallingSites callingSites = new CallingSites();
                callingSites.callingmain(searchtext);
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    pd.dismiss();
                    switch(catSelector){
                        case 0:
                            initialSize = arraySave.products.size();
                            adapter = new MyAdapterGeneral(Main2Activity.this, arraySave.products);
                            recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                            recyclerView.setAdapter(adapter);
                            keepRefreshing();
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
                }, 2000);
                searchBar.setText("");
            }
        });

        findViewById(R.id.navigation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        switch(catSelector){
            case 0:
                initialSize = arraySave.products.size();
                adapter = new MyAdapterGeneral(Main2Activity.this, arraySave.products);
                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                recyclerView.setAdapter(adapter);
                keepRefreshing();
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
    }

    public void keepRefreshing(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean condition = false;
                if(!condition) {
                    if (initialSize < arraySave.products.size()) {
                        adapter.notifyDataSetChanged();
                        initialSize = arraySave.products.size();
                        handler.postDelayed(this, 1500);
                    }else{
                        condition = false;
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
            Intent intent = new Intent(this, Main_General_category.class);
            startActivity(intent);
            finish();
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