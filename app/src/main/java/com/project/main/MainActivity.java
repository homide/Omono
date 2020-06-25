package com.project.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>(); // all products combine
    ArrayList<String> producturl = new ArrayList<String>();
    ArrayList<String> imageurls = new ArrayList<String>();


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

                    final Flipkart flip = new Flipkart();
                    final Paytm pyt = new Paytm();
                    final Snapdeal snap = new Snapdeal();
                    final ShopClues shopclues = new ShopClues();

                    Thread t1 = new Thread() {
                        public void run() {
                            flip.execute("https://www.flipkart.com/search?q=" + searchtext + "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off");
                        }
                    };

                    Thread t2 = new Thread() {
                        public void run() {
                            pyt.execute("https://www.paytmmall.com/shop/search?q=" + searchtext + "&from=organic&child_site_id=6");
                        }
                    };

                    Thread t3 = new Thread() {
                        public void run() {
                            snap.execute("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                                    "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                                    "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy");

                        }
                    };


                    Thread t4 = new Thread() {
                        public void run(){
                            shopclues.execute("https://www.shopclues.com/search?q="+searchtext +"&sc_z=2222&z=0&count=10");
                        }
                    };
               //     t1.start();
                    t2.start();
              //      t3.start();
               //     t4.start();

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
                    },4000);

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
                    },4000);

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
                    },4000);

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
                    },4000);


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Main2Activity m2a = new Main2Activity();
                            m2a.listview = null;
                            pd.dismiss();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST", (Serializable) allproducts);
                            args.putSerializable("URLLINKS", (Serializable) producturl);
                            args.putSerializable("IMAGEURL", (Serializable) imageurls);
                            args.putSerializable("PRODUCTTITLE", (Serializable)titleallproducts);
                            intent.putExtra("BUNDLE", args);
                            intent.putExtra(EXTRA_TEXT, searchtext);
                            startActivity(intent);
                        }
                    }, 5000);

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
