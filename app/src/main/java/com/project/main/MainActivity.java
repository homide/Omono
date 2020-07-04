package com.project.main;  //project-custom-package

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class
MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public EditText searchbar;
    public Button searchButton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    public static final String EXTRA_TEXT = "com.kush.naya.EXTRA_TEXT";
    String searchtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbar = (EditText) findViewById(R.id.searchText1);
        searchButton = (Button) findViewById(R.id.btnSearch1);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navView);

        navigationView.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingmain(searchtext);
//                    CallingGrocery callingGrocery = new CallingGrocery();
//                    callingGrocery.callinggrocery(searchtext);
                    //Class Initiations

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra(EXTRA_TEXT, searchtext);
                            startActivity(intent);
                        }
                    }, 2500);

                }
            }

        });
    }

    @Override
    public void onBackPressed(){

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}