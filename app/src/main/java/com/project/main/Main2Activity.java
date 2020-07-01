//Refer to MainActivity for Commenting

package com.project.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity implements arraySave {
    //test

    public ListView listview;
    public Button button2;
    public EditText usersearch;

    TextView resultText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        getSupportActionBar().setTitle("Main2Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listview = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        String input1 = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        resultText= (TextView) findViewById(R.id.resultText);
        resultText.setText("Showing Results for: "+input1);
        final MyAdaptor adaptor = new MyAdaptor(Main2Activity.this,arraySave.allproducts,arraySave.producturl,arraySave.titleallproducts,arraySave.imageurls);

        if (arraySave.producturl.size() > 0){
            listview.setAdapter(adaptor);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String link = arraySave.producturl.get(position);
                    Intent intent = new Intent((Intent.ACTION_VIEW));
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            });
        }
        runOnUiThread(new Runnable() {
            public void run() {
                adaptor.notifyDataSetChanged();
            }
        });


        button2 = (Button) findViewById(R.id.btnSearch2);
        usersearch = (EditText) findViewById(R.id.searchText2);

        //OnClick Listener
        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                if (usersearch.getText().length() <= 0) {
                    Toast.makeText(Main2Activity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                } else {
                    arraySave.producturl.clear();
                    arraySave.imageurls.clear();
                    arraySave.titleallproducts.clear();
                    arraySave.allproducts.clear();
                    final ProgressDialog pd = new ProgressDialog(Main2Activity.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    CallingMain callingMain = new CallingMain();
                    callingMain.callingmain(usersearch.getText().toString());
                    final MyAdaptor adaptor = new MyAdaptor(Main2Activity.this,arraySave.allproducts,arraySave.producturl,arraySave.titleallproducts,arraySave.imageurls);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resultText.setText("Showing Results for: "+usersearch.getText());
                            pd.dismiss();
                            listview.setAdapter(adaptor);
                        }
                    }, 2500);

                    runOnUiThread(new Runnable() {
                        public void run() {
                            adaptor.notifyDataSetChanged();
                        }
                    });

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String link = arraySave.producturl.get(position);
                            Intent intent = new Intent((Intent.ACTION_VIEW));
                            intent.setData(Uri.parse(link));
                            startActivity(intent);
                        }
                    });
                }
            }
        });
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


    @Override
    public void onBackPressed(){
        arraySave.producturl.clear();
        arraySave.imageurls.clear();
        arraySave.titleallproducts.clear();
        arraySave.allproducts.clear();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                arraySave.producturl.clear();
                arraySave.imageurls.clear();
                arraySave.titleallproducts.clear();
                arraySave.allproducts.clear();
                break;
        }
        return true;
    }


    }



