package com.project.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.main.Wishlist.WishlistActivity;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Button send = (Button) findViewById(R.id.sendButton);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"chauhankanishk990@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT, "Body of email");
                try{
                    startActivity(Intent.createChooser(i,"Send mail...."));
                }catch (Exception e){
                    Toast.makeText(Feedback.this, "No email client installed", Toast.LENGTH_SHORT).show();
                    System.out.println("Email not send");
                }
            }
        });




        //notification icon on toolbar
        ImageView notifications_toolbar_icon = (ImageView) findViewById(R.id.notifications_toolbar_icon);
        notifications_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Feedback.this, Notifications.class);
                startActivity(cinemaIntent);
            }
        });

        //category icon on toolbar
        ImageView category_toolbar_icon = (ImageView) findViewById(R.id.category_toolbar_icon);
        category_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Feedback.this, SelectCategoryClass.class);
                startActivity(cinemaIntent);
            }
        });

        //wishlist icon on toolbar
        ImageView wishlist_toolbar_icon = (ImageView) findViewById(R.id.wishlist_toolbar_icon);
        wishlist_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Feedback.this, WishlistActivity.class);
                startActivity(cinemaIntent);
            }
        });

        //appLogo click to home page
//        ImageView appLogo = (ImageView) findViewById(R.id.appLogo);
//        appLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cinemaIntent = new Intent(Feedback.this, MainActivity.class);
//                startActivity(cinemaIntent);
//            }
//        });


    }
}