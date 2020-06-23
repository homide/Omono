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


public class Main2Activity extends AppCompatActivity {

    public ListView listview;
    public Button button2;
    public EditText usersearch;
    public String search;
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>(); // all products combine
    ArrayList<String> producturl = new ArrayList<String>();
    ArrayList<String> imageurls = new ArrayList<String>();
    ArrayList<String> temparraylist;
    ArrayList<String> tempurllist;
    ArrayList<String> tempimageurl;
    ArrayList<String> tempproducttitle;
    TextView resultText;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listview = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent();
        String input1 = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        Bundle args = intent.getBundleExtra("BUNDLE");
        temparraylist = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        tempurllist = (ArrayList<String>) args.getSerializable("URLLINKS");
        tempimageurl = (ArrayList<String>) args.getSerializable("IMAGEURL");
        tempproducttitle = (ArrayList<String>) args.getSerializable("PRODUCTTITLE");
        resultText= (TextView) findViewById(R.id.resultText);
        resultText.setText("Showing Results for: "+input1);


        if (temparraylist.isEmpty() != true) {
            MyAdaptor adaptor = new MyAdaptor(Main2Activity.this,temparraylist,tempurllist,tempproducttitle,tempimageurl);
            listview.setAdapter(adaptor);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String link = tempurllist.get(position);
                    Intent intent = new Intent((Intent.ACTION_VIEW));
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            });
        }


        button2 = (Button) findViewById(R.id.btnSearch2);
        usersearch = (EditText) findViewById(R.id.searchText2);



        button2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {

                if (usersearch.getText().length() <= 0) {
                    Toast.makeText(Main2Activity.this, "Please add something to search.", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog pd = new ProgressDialog(Main2Activity.this);
                    pd.setMessage("Searching websites...");
                    pd.show();
                    if(allproducts.size() > 0){
                        allproducts.clear();
                    }
                    if(producturl.size() >0){
                        producturl.clear();
                    }
                    if(titleallproducts.size() > 0){
                        titleallproducts.clear();
                    }
                    if(imageurls.size()>0){
                        imageurls.clear();
                    }
                    final MyAdaptor adaptor = new MyAdaptor(Main2Activity.this,allproducts,producturl,titleallproducts,imageurls);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resultText.setText("Showing Results for: "+usersearch.getText());
                            pd.dismiss();
                            listview.setAdapter(adaptor);
                        }
                    }, 6000);

                    final Flipkart flip = new Flipkart();
                    final Paytm pyt = new Paytm();
                    final Snapdeal snap = new Snapdeal();
                    final ShopClues shopclues = new ShopClues();

                    Thread t1 = new Thread() {
                        public void run() {
                            flip.execute("https://www.flipkart.com/search?q=" + usersearch.getText() + "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off");
                        }
                    };

                    Thread t2 = new Thread() {
                        public void run() {
                            pyt.execute("https://www.paytmmall.com/shop/search?q=" +usersearch.getText() + "&from=organic&child_site_id=6");
                        }
                    };

                    Thread t3 = new Thread() {
                        public void run() {
                            snap.execute("https://www.snapdeal.com/search?keyword=" + usersearch.getText() + "&santizedKeyword=&catId=" +
                                    "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                                    "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy");

                        }
                    };


                    Thread t4 = new Thread() {
                        public void run(){
                            shopclues.execute("https://www.shopclues.com/search?q="+ usersearch.getText() +"&sc_z=2222&z=0&count=10");
                        }
                    };
                    t1.start();
                    t2.start();
                    t3.start();
                    t4.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<ArrayList> allArrays = snap.arrayReturn();
                            ArrayList<String> temparr1 = allArrays.get(0);
                            ArrayList<String> temparr2 = allArrays.get(1);
                            ArrayList<String> temparr3 = allArrays.get(2);
                            ArrayList<String> temparr4 = allArrays.get(3);

                            titleallproducts.addAll(temparr1);
                            allproducts.addAll(temparr2);
                            producturl.addAll(temparr3);
                            imageurls.addAll(temparr4);

                        }
                    },1000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<ArrayList> allArrays = flip.arrayReturn();
                            ArrayList<String> temparr1 = allArrays.get(0);
                            ArrayList<String> temparr2 = allArrays.get(1);
                            ArrayList<String> temparr3 = allArrays.get(2);
                            ArrayList<String> temparr4 = allArrays.get(3);

                            titleallproducts.addAll(temparr1);
                            allproducts.addAll(temparr2);
                            producturl.addAll(temparr3);
                            imageurls.addAll(temparr4);

                        }
                    },1500);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<ArrayList> allArrays = pyt.arrayReturn();
                            ArrayList<String> temparr1 = allArrays.get(0);
                            ArrayList<String> temparr2 = allArrays.get(1);
                            ArrayList<String> temparr3 = allArrays.get(2);
                            ArrayList<String> temparr4 = allArrays.get(3);

                            titleallproducts.addAll(temparr1);
                            allproducts.addAll(temparr2);
                            producturl.addAll(temparr3);
                            imageurls.addAll(temparr4);

                        }
                    },2000);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<ArrayList> allArrays = shopclues.arrayReturn();
                            ArrayList<String> temparr1 = allArrays.get(0);
                            ArrayList<String> temparr2 = allArrays.get(1);
                            ArrayList<String> temparr3 = allArrays.get(2);
                            ArrayList<String> temparr4 = allArrays.get(3);

                            titleallproducts.addAll(temparr1);
                            allproducts.addAll(temparr2);
                            producturl.addAll(temparr3);
                            imageurls.addAll(temparr4);

                        }
                    },2500);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String link = producturl.get(position);
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        super.onBackPressed();
    }

}

