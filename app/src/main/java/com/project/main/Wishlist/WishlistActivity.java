package com.project.main.Wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.project.main.DatabaseHelper;
import com.project.main.Notifications;
import com.project.main.R;
import com.project.main.SelectCategoryClass;

public class WishlistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    TabLayout tabLayout;
    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        drawerLayout = findViewById(R.id.drawer_layout);

        tabLayout = findViewById(R.id.tabLayout);
        frameLayout = findViewById(R.id.frameLayout);

        fragment = new GeneralWishes(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new GeneralWishes(WishlistActivity.this);
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


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
                startActivity(new Intent(WishlistActivity.this, Notifications.class));
            }
        });

        findViewById(R.id.category_toolbar_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WishlistActivity.this, SelectCategoryClass.class));
            }
        });

        navigationDrawer();
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
            Intent cinemaIntent = new Intent(WishlistActivity.this, Notifications.class);
            startActivity(cinemaIntent);
            finish();
        }
        else if (id == R.id.wishlist){

        }
        else if (id == R.id.category){
            Intent intent = new Intent(WishlistActivity.this, SelectCategoryClass.class);
            startActivity(intent);
            finish();
        }
        else if(id == R.id.myAccount || id == R.id.settings || id == R.id.legalAndAbout){
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