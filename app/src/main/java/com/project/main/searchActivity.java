package com.project.main;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class searchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    public Button searchButton;
//    public EditText searchbar;
    NavigationView navigationView2;
//    Toolbar toolbar2;
//    public String searchtext;
    RelativeLayout generalCat, groceriesCat, fashionCat, medCat, electroCat;
    DrawerLayout drawerLayout2;
//    public static final String EXTRA_TEXT = "com.omono.naya.EXTRA_TEXT";
    int categorySelector = 0;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        searchButton = (Button) findViewById(R.id.btnSearch);
//        searchbar = (EditText) findViewById(R.id.searchText);

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

        drawerLayout2 = findViewById(R.id.drawer_layout2);
//        toolbar2 = findViewById(R.id.toolbar);
        navigationView2 = findViewById(R.id.navView2);
        navigationView2.bringToFront();
//        setSupportActionBar(toolbar2);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout2, toolbar2, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
//        drawerLayout2.addDrawerListener(toggle);
//        toggle.syncState();

//        MainActivity obj= new MainActivity();


//        //notification icon on toolbar
//        ImageView notifications_toolbar_icon = (ImageView) findViewById(R.id.notifications_toolbar_icon);
//        notifications_toolbar_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cinemaIntent = new Intent(searchActivity.this, Notifications.class);
//                startActivity(cinemaIntent);
//            }
//        });
//
//        //category icon on toolbar
//        ImageView category_toolbar_icon = (ImageView) findViewById(R.id.category_toolbar_icon);
//        category_toolbar_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cinemaIntent = new Intent(searchActivity.this, searchActivity.class);
//                startActivity(cinemaIntent);
//            }
//        });

//        //appLogo click to home page
//        ImageView appLogo = (ImageView) findViewById(R.id.appLogo);
//        appLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cinemaIntent = new Intent(searchActivity.this, MainActivity.class);
//                startActivity(cinemaIntent);
//            }
//        });

        //wishlist icon on toolbar
        ImageView wishlist_toolbar_icon = (ImageView) findViewById(R.id.wishlist_toolbar_icon);
        wishlist_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(searchActivity.this, Wishlist.class);
                startActivity(cinemaIntent);
            }
        });


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

//        searchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchtext = searchbar.getText().toString();
//                if (searchtext.length() <= 0) {
//                    Toast.makeText(searchActivity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    arraySave.products.clear();
//                    final ProgressDialog pd = new ProgressDialog(searchActivity.this);
//                    pd.setMessage("Searching websites...");
//                    pd.show();
//                    CallingMain callingMain = new CallingMain();
//                    switch(categorySelector){
//                        case 0:
//                            callingMain.callingmain(searchtext);
//                            break;
//                        case 1:
//                            callingMain.callinggrocery(searchtext);
//                            break;
//                        case 2:
//                            callingMain.callingfashion(searchtext);
//                            break;
//                        case 3:
//                            callingMain.callingmedicines(searchtext);
//                            break;
//                        case 4:
//                            callingMain.callingelectronics(searchtext);
//                            break;
//                    }
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        public void run() {
//                            pd.dismiss();
//                            Intent intent = new Intent(searchActivity.this, Main2Activity.class);
//                            startActivity(intent);
//                        }
//                    },2500 );
//                }
//            }
//        });
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