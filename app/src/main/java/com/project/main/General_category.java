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

public class General_category extends AppCompatActivity implements arraySave {

    public Button searchButton;
    public EditText searchbar;
    public String searchtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_category);
        searchButton = (Button) findViewById(R.id.btnSearch);
        searchbar = (EditText) findViewById(R.id.searchText);

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
}