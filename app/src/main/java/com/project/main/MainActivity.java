package com.project.main;  //project-custom-package

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    LinearLayout toolbar1;
    ViewFlipper viewFlipper;
    public RelativeLayout searchbar;
    ImageView notifications_toolbar_icon, category_toolbar_icon, wishlist_toolbar_icon, navigation_btn;
    ConstraintLayout contentView;
    ImageView instaPage, fbPage,twitterPage;
//    ImageView appLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int images[] = {R.drawable.card_1,R.drawable.card_2,R.drawable.card_3,R.drawable.card_4,R.drawable.card_5};
        viewFlipper = findViewById(R.id.viewFlipper);
        for(int i=0;i<images.length;i++) {
            setFlipperImage(images[i]);
        }
        searchbar =  findViewById(R.id.buttonBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar1 = findViewById(R.id.btntoolbar);
        contentView = findViewById(R.id.content1);

        //Hamburger buttons
        instaPage = findViewById(R.id.insta_btn);
        instaPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.instagram.com";
                Intent intent = new Intent((Intent.ACTION_VIEW));
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        fbPage = findViewById(R.id.fb_btn);
        fbPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://www.facebook.com";
                Intent intent = new Intent((Intent.ACTION_VIEW));
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        twitterPage = findViewById(R.id.twitter_btn);
        twitterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "https://twitter.com";
                Intent intent = new Intent((Intent.ACTION_VIEW));
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });


        //notification icon on toolbar
        notifications_toolbar_icon=(ImageView)findViewById(R.id.notifications_toolbar_icon);
        notifications_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(MainActivity.this, Notifications.class);
                startActivity(cinemaIntent);
            }
        });

        //wishlist icon on toolbar
        wishlist_toolbar_icon=findViewById(R.id.wishlist_toolbar_icon);
        wishlist_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(MainActivity.this, Wishlist.class);
                startActivity(cinemaIntent);
            }
        });

        navigationDrawer();

        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, searchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setFlipperImage(int res) {
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }

    private void navigationDrawer() {
        //hamburger
        navigationView = findViewById(R.id.navView);
        navigation_btn = findViewById(R.id.navigation_btn);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        navigation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

//        navigationDrawerAnimation();
    }

//    private void navigationDrawerAnimation() {
//
////        drawerLayout.setScrimColor(getResources().getColor(R.color.card1));
//        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//
//                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
//                final float offsetScale = 1 - diffScaledOffset;
//                contentView.setScaleX(offsetScale);
//                contentView.setScaleY(offsetScale);
//
//                final float xOffset = drawerView.getWidth() * slideOffset;
//                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
//                final float xTranslation = xOffset - xOffsetDiff;
//                contentView.setTranslationX(xTranslation);
//            }
//        });
//
//    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here
        int id = item.getItemId();

        if (id == R.id.notifications){
            Intent cinemaIntent = new Intent(this, Notifications.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.wishlist){
            Intent cinemaIntent = new Intent(this, Wishlist.class);
            startActivity(cinemaIntent);
        }
        else if (id == R.id.category){
            Intent intent = new Intent(this, searchActivity.class);
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

}
