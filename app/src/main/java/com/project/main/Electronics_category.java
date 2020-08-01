package com.project.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class Electronics_category extends AppCompatActivity implements arraySave, NavigationView.OnNavigationItemSelectedListener {

    public AutoCompleteTextView searchbar;
    public String searchtext;
    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics_category);

        int images[] = {R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4, R.drawable.card_5};
        viewFlipper = findViewById(R.id.viewFlipper);
        for (int i = 0; i < images.length; i++) {
            setFlipperImage(images[i]);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        searchbar = findViewById(R.id.searchText);

        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(Electronics_category.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                } else {
                    arraySave.products.clear();
                    final ProgressDialog pd = new ProgressDialog(Electronics_category.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingelectronics(searchtext);
                    Handler handler = new Handler();
                    do {
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                pd.dismiss();
                                Intent intent = new Intent(Electronics_category.this, Main2Activity.class);
                                startActivity(intent);
                            }
                        }, 1700);
                    } while (arraySave.products.size() > 10);
                }
            }
        });

        String[] suggestions = getResources().getStringArray(R.array.suggestions);
        searchbar.setAdapter(new ArrayAdapter<>(Electronics_category.this, android.R.layout.simple_list_item_1, suggestions));

        findViewById(R.id.insta_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.instagram.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        findViewById(R.id.fb_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.facebook.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        findViewById(R.id.twitter_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://twitter.com";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });

        findViewById(R.id.notifications_toolbar_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Electronics_category.this, Notifications.class));
            }
        });

        findViewById(R.id.wishlist_toolbar_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Electronics_category.this, Wishlist.class));
            }
        });

        findViewById(R.id.category_toolbar_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Electronics_category.this, searchActivity.class));
            }
        });

        navigationDrawer();
    }

    private void setFlipperImage(int res) {
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }

    private void navigationDrawer() {
        //hamburger
        navigationView = findViewById(R.id.navView);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.navigation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        int id = item.getItemId();

        if (id == R.id.notifications){
            Intent cinemaIntent = new Intent(Electronics_category.this, Notifications.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.wishlist){
            Intent cinemaIntent = new Intent(Electronics_category.this, Wishlist.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.category){
            Intent intent = new Intent(Electronics_category.this, searchActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.myAccount || id == R.id.settings || id == R.id.legalAndAbout|| id == R.id.contactus){
            Toast.makeText(this, "This doesn't have a function yet", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}