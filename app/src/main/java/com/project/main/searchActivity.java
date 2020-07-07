package com.project.main;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class searchActivity extends AppCompatActivity {

    public Button searchButton;
    public EditText searchbar;
    public NavigationView navigationView2;
    public Toolbar toolbar;
    public String searchtext;
    public DrawerLayout drawerLayout2;
    public static final String EXTRA_TEXT = "com.omono.naya.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchbar = (EditText) findViewById(R.id.searchText);

        drawerLayout2 = findViewById(R.id.drawer_layout3);
        toolbar = findViewById(R.id.toolbar);
        navigationView2 = findViewById(R.id.navView2);

        navigationView2.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout2, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout2.addDrawerListener(toggle);
        toggle.syncState();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(searchActivity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog pd = new ProgressDialog(searchActivity.this);
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
                            Intent intent = new Intent(searchActivity.this, Main2Activity.class);
                            intent.putExtra(EXTRA_TEXT, searchtext);
                            startActivity(intent);
                        }
                    }, 2000);

                }
            }

        });
    }
}