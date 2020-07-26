package com.project.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Medicine_category extends AppCompatActivity {

    public Button searchButton;
    public EditText searchbar;
    public String searchtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_category);

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchbar = (EditText) findViewById(R.id.searchText);
        ImageView notifications_toolbar_icon, category_toolbar_icon, wishlist_toolbar_icon, navigation_btn;

        notifications_toolbar_icon=(ImageView)findViewById(R.id.notifications_toolbar_icon);
        notifications_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Medicine_category.this, Notifications.class);
                startActivity(cinemaIntent);
            }
        });

        //category icon on toolbar
        category_toolbar_icon=findViewById(R.id.category_toolbar_icon);
        category_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Medicine_category.this, searchActivity.class);
                startActivity(cinemaIntent);
            }
        });

        //wishlist icon on toolbar
        wishlist_toolbar_icon=findViewById(R.id.wishlist_toolbar_icon);
        wishlist_toolbar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cinemaIntent = new Intent(Medicine_category.this, Wishlist.class);
                startActivity(cinemaIntent);
            }
        });



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(Medicine_category.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                }
                else {
                    arraySave.products.clear();
                    final ProgressDialog pd = new ProgressDialog(Medicine_category.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingmedicines(searchtext);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            Intent intent = new Intent(Medicine_category.this, Main2Activity.class);
                            startActivity(intent);
                        }
                    },2500);
                }
            }
        });
    }
}