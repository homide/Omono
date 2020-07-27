package com.project.main;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class General_category extends AppCompatActivity implements arraySave {

    public Button searchButton;
    public EditText searchbar;
    public String searchtext;
    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView notifications_toolbar_icon, category_toolbar_icon, wishlist_toolbar_icon, navigation_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_category);
        searchButton = (Button) findViewById(R.id.btnSearch);
        searchbar = (EditText) findViewById(R.id.searchText);

        int images[] = {R.drawable.card_1,R.drawable.card_2,R.drawable.card_3,R.drawable.card_4,R.drawable.card_5};
        viewFlipper = findViewById(R.id.viewFlipper);
        for(int i=0;i<images.length;i++) {
            setFlipperImage(images[i]);
        }

        notifications_toolbar_icon=(ImageView)findViewById(R.id.notifications_toolbar_icon);
        notifications_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(General_category.this, Notifications.class);
                startActivity(cinemaIntent);
            }
        });

        //category icon on toolbar
        category_toolbar_icon=findViewById(R.id.category_toolbar_icon);
        category_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(General_category.this, searchActivity.class);
                startActivity(cinemaIntent);
            }
        });

        //wishlist icon on toolbar
        wishlist_toolbar_icon=findViewById(R.id.wishlist_toolbar_icon);
        wishlist_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(General_category.this, Wishlist.class);
                startActivity(cinemaIntent);
            }
        });

//        navigationDrawer();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(General_category.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                }
                else {
                    arraySave.products.clear();
                    final ProgressDialog pd = new ProgressDialog(General_category.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingmain(searchtext);
                    Handler handler = new Handler();
                    do {
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                pd.dismiss();
                                Intent intent = new Intent(General_category.this, Main2Activity.class);
                                startActivity(intent);
                            }
                        }, 1700);
                    }while (arraySave.products.size() > 10);
                }
            }
        });

    }

    private void setFlipperImage(int res) {
        ImageView image = new ImageView(getApplicationContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
    }

//    private void navigationDrawer() {
//        //hamburger
//        navigationView = findViewById(R.id.navView);
//        navigation_btn = findViewById(R.id.navigation_btn);
//        navigationView.bringToFront();
//        navigationView.setNavigationItemSelectedListener(General_category.this);
//        navigation_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//
//        navigationDrawerAnimation();
//    }

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
//
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here
//        int id = item.getItemId();
//
//        if (id == R.id.notifications){
//            Intent cinemaIntent = new Intent(this, Notifications.class);
//            startActivity(cinemaIntent);
//        }
//        else if (id == R.id.wishlist){
//            Intent cinemaIntent = new Intent(this, Wishlist.class);
//            startActivity(cinemaIntent);
//        }
//        else if (id == R.id.category){
//            Intent intent = new Intent(this, searchActivity.class);
//            startActivity(intent);
//        }
//        else if(id == R.id.myAccount || id == R.id.settings || id == R.id.legalAndAbout|| id == R.id.contactus){
//            Toast.makeText(this, "This doesn't have a function yet", Toast.LENGTH_SHORT).show();
//        }
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}