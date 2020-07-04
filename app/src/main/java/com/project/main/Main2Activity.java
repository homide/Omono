//Refer to MainActivity for Commenting

package com.project.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;


public class Main2Activity extends AppCompatActivity implements arraySave, NavigationView.OnNavigationItemSelectedListener {
    //test
    public Button button2;
    public RecyclerView recyclerView;
    public EditText usersearch;
    public MyAdaptor adaptor;
    DrawerLayout drawerLayout2;
    NavigationView navigationView2;
    Toolbar toolbar;

    TextView resultText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout2 = findViewById(R.id.drawer_layout2);
        toolbar = findViewById(R.id.toolbar);
        navigationView2 = findViewById(R.id.navView2);

        navigationView2.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout2, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout2.addDrawerListener(toggle);
        toggle.syncState();

        navigationView2.setNavigationItemSelectedListener(this);
//        getSupportActionBar().setTitle("Main2Activity");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        String input1 = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        adaptor = new MyAdaptor(Main2Activity.this, arraySave.pricebefore, arraySave.discountedprice, arraySave.discount, arraySave.producturl, arraySave.titleallproducts, arraySave.imageurls,arraySave.tag);
        if (arraySave.producturl.size() > 0) {
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

            button2 = (Button) findViewById(R.id.btnSearch1);
            usersearch = (EditText) findViewById(R.id.searchText1);

            //OnClick Listener
            button2.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("StaticFieldLeak")
                @Override
                public void onClick(View v) {

                    if (usersearch.getText().length() <= 0) {
                        Toast.makeText(Main2Activity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        arraySave.producturl.clear();
                        arraySave.imageurls.clear();
                        arraySave.titleallproducts.clear();
                        arraySave.pricebefore.clear();
                        arraySave.discountedprice.clear();
                        arraySave.discount.clear();
                        final ProgressDialog pd = new ProgressDialog(Main2Activity.this);
                        pd.setMessage("Searching websites...");
                        pd.show();
                        CallingMain callingMain = new CallingMain();
                        callingMain.callingmain(usersearch.getText().toString());
                        adaptor = new MyAdaptor(Main2Activity.this, arraySave.pricebefore, arraySave.discountedprice, arraySave.discount, arraySave.producturl, arraySave.titleallproducts, arraySave.imageurls,arraySave.tag);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                pd.dismiss();
                                recyclerView.setAdapter(adaptor);
                                recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                            }
                        }, 2500);
                    }
                }
            });
        }
    @Override
    protected void onStart () {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState (@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onRestart () {
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause () {
        super.onPause();
    }

    @Override
    protected void onPostResume () {
        super.onPostResume();
    }

    @Override
    protected void onStop () {
        super.onStop();
    }


    @Override
    public void onBackPressed () {
        arraySave.producturl.clear();
        arraySave.imageurls.clear();
        arraySave.titleallproducts.clear();
        arraySave.pricebefore.clear();
        arraySave.discountedprice.clear();
        arraySave.discount.clear();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                arraySave.producturl.clear();
                arraySave.imageurls.clear();
                arraySave.titleallproducts.clear();
                arraySave.pricebefore.clear();
                arraySave.discountedprice.clear();
                arraySave.discount.clear();
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}