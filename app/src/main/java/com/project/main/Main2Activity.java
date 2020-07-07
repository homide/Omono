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
    public Button searchbar;
    public RecyclerView recyclerView;
    public MyAdaptor adaptor;
    DrawerLayout drawerLayout3;
    NavigationView navigationView3;
    Toolbar toolbar;

    TextView resultText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        drawerLayout3 = findViewById(R.id.drawer_layout3);
        toolbar = findViewById(R.id.toolbar);
        navigationView3 = findViewById(R.id.navView2);

        navigationView3.bringToFront();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout3, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawerLayout3.addDrawerListener(toggle);
        toggle.syncState();

        navigationView3.setNavigationItemSelectedListener(this);
//        getSupportActionBar().setTitle("Main2Activity");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Intent intent = getIntent();
        String input1 = intent.getStringExtra(searchActivity.EXTRA_TEXT);
        adaptor = new MyAdaptor(Main2Activity.this, arraySave.products);
        if (arraySave.products.size() > 0) {
            recyclerView.setAdapter(adaptor);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        if (input1.length() <= 0) {
            Toast.makeText(Main2Activity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
        }
        else {
            arraySave.products.clear();
            final ProgressDialog pd = new ProgressDialog(Main2Activity.this);
            pd.setMessage("Searching websites...");
            pd.show();
            CallingMain callingMain = new CallingMain();
            callingMain.callingmain(input1);
            adaptor = new MyAdaptor(Main2Activity.this, arraySave.products);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    pd.dismiss();
                    recyclerView.setAdapter(adaptor);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                }
            }, 2500);
        }

        searchbar = (Button) findViewById(R.id.buttonBar);
        searchbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, searchActivity.class);
                startActivity(intent);
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
        arraySave.products.clear();
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
                arraySave.products.clear();
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }
}