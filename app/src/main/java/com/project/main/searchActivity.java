package com.project.main;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class searchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Button searchButton;
    public EditText searchbar;
    NavigationView navigationView2;
    Toolbar toolbar2;
    public String searchtext;
    DrawerLayout drawerLayout2;
    public static final String EXTRA_TEXT = "com.omono.naya.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchbar = (EditText) findViewById(R.id.searchText);

        drawerLayout2 = findViewById(R.id.drawer_layout2);
        toolbar2 = findViewById(R.id.toolbar);
        navigationView2 = findViewById(R.id.navView2);

        navigationView2.bringToFront();
        setSupportActionBar(toolbar2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout2, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout2.addDrawerListener(toggle);
        toggle.syncState();

        navigationView2.setNavigationItemSelectedListener(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(searchActivity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                }
                else {
                    arraySave.products.clear();
                    final ProgressDialog pd = new ProgressDialog(searchActivity.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingfashion(searchtext);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            Intent intent = new Intent(searchActivity.this, Main2Activity.class);
                            startActivity(intent);
                        }
                    },2500 );
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout2.isDrawerOpen(GravityCompat.START)) {
            drawerLayout2.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
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
        else
            Toast.makeText(this, "This doesn't have a funtion yet", Toast.LENGTH_SHORT).show();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}