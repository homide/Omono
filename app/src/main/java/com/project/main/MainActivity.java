package com.project.main;  //project-custom-package

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar1;
    public Button searchbar;
    private MenuItem menuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbar = (Button) findViewById(R.id.buttonBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar1 = findViewById(R.id.toolbar);

        //hamburger
        navigationView = findViewById(R.id.navView);
        navigationView.bringToFront();
        setSupportActionBar(toolbar1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar1, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, searchActivity.class);
                startActivity(intent);
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

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        this.menuItem = menuItem;
//        return true;
//    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent cinemaIntent = new Intent(this, MainActivity.class);
            startActivity(cinemaIntent);
            arraySave.products.clear();
        } else if (id == R.id.feedback){
            Intent cinemaIntent = new Intent(this, Feedback.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.notifications){
            Intent cinemaIntent = new Intent(this, Notifications.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.wishlist){
            Intent cinemaIntent = new Intent(this, Wishlist.class);
            startActivity(cinemaIntent);
        }
        else
            Toast.makeText(this, "This doesn't have a function yet", Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.search) {
//            Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_LONG).show();
//            return true;
//        } else if(id == R.id.setting) {
//            Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_LONG).show();
//            return true;
//        } else if(id == R.id.about) {
//            Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_LONG).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
