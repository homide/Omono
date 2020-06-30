package com.project.main;  //project-custom-package

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public ListView listview;
    public EditText searchbar;
    public Button searchButton;
    public static final String EXTRA_TEXT = "com.kush.naya.EXTRA_TEXT";
    String searchtext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchbar = (EditText) findViewById(R.id.searchText1);
        searchButton = (Button) findViewById(R.id.btnSearch1);
        listview = (ListView) findViewById(R.id.listView);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("Searching websites...");
                    pd.show();

/////////////////////////////////////////////////////////////////////////////////////////////////
                    // Temparray1 contains - title of all products
                    // Temparray2 contains - all product details
                    //Temparray3 contains - all product urls
                    //Temparray3 contains - all product image urls
/////////////////////////////////////////////////////////////////////////////////////////////////





                    CallingMain callingMain = new CallingMain();
                    callingMain.callingmain(searchtext);
                    //Class Initiations

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pd.dismiss();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra(EXTRA_TEXT, searchtext);
                            startActivity(intent);
                        }
                    }, 2500);

                }
            }

        });

    }

    //Main menu buttons



    public void flipkartButton(View view){
        Intent intent = new Intent((Intent.ACTION_VIEW));
        intent.setData(Uri.parse("https://www.flipkart.com"));
        startActivity(intent);
    }

    public void shopcluesButton(View view){
        Intent intent = new Intent((Intent.ACTION_VIEW));
        intent.setData(Uri.parse("https://www.shopclues.com"));
        startActivity(intent);
    }

    public void snapdealButton(View view){
        Intent intent = new Intent((Intent.ACTION_VIEW));
        intent.setData(Uri.parse("https://www.snapdeal.com"));
        startActivity(intent);
    }

    public void paytmButton(View view){
        Intent intent = new Intent((Intent.ACTION_VIEW));
        intent.setData(Uri.parse("https://www.paytmmall.com"));
        startActivity(intent);
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
