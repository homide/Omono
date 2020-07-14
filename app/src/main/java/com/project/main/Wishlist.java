package com.project.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class Wishlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);


        //notification icon on toolbar
        ImageView notifications_toolbar_icon = (ImageView) findViewById(R.id.notifications_toolbar_icon);
        notifications_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Wishlist.this, Notifications.class);
                startActivity(cinemaIntent);
            }
        });

        //category icon on toolbar
        ImageView category_toolbar_icon = (ImageView) findViewById(R.id.category_toolbar_icon);
        category_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Wishlist.this, searchActivity.class);
                startActivity(cinemaIntent);
            }
        });

        //wishlist icon on toolbar
        ImageView wishlist_toolbar_icon = (ImageView) findViewById(R.id.wishlist_toolbar_icon);
        wishlist_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Wishlist.this, Wishlist.class);
                startActivity(cinemaIntent);
            }
        });

        //appLogo click to home page
        ImageView appLogo = (ImageView) findViewById(R.id.appLogo);
        appLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Wishlist.this, MainActivity.class);
                startActivity(cinemaIntent);
            }
        });


    }

    public void addProduct(ArrayList<Product> product){

    }
}