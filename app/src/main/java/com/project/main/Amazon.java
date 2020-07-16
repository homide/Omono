package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Amazon extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {

    //ArrayList for corresponding objects
    ArrayList<Product> products = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<Product> s) {

        super.onPostExecute(s);
        arraySave.products.addAll(products);

    }

    @Override
    protected ArrayList<Product> doInBackground(String... strings) {
        try{

            //initialising calling.java and referencing it
            System.out.println("Running Amazon on thread");
            CallingGeneral calling = new CallingGeneral();
            link = strings[0];
            calling.call(5,strings[0],"celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results","a","a-link-normal a-text-normal","a-price-whole","a-offscreen",
                    "img","","a-size-base","a-icon-alt");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended Amazon on thread");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Amazon not working" + e);
            return null;
        }
    }
}
