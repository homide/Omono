package com.project.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fashion_category extends AppCompatActivity {

    public Button searchButton;
    public EditText searchbar;
    public String searchtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_category);

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchbar = (EditText) findViewById(R.id.searchText);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext = searchbar.getText().toString();
                if (searchtext.length() <= 0) {
                    Toast.makeText(Fashion_category.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                }
                else {
                    arraySave.products.clear();
                    final ProgressDialog pd = new ProgressDialog(Fashion_category.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingfashion(searchtext);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            pd.dismiss();
                            Intent intent = new Intent(Fashion_category.this, Main2Activity.class);
                            startActivity(intent);
                        }
                    },2500 );
                }
            }
        });

    }
}