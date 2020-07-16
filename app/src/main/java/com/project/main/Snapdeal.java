//Refer to MainActivity, Main2Activity and Calling for commenting references

package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Snapdeal extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave{

    //ArrayList for corresponding objects
    ArrayList<Product> products = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<Product> s) {
        super.onPostExecute(s);
        try {
            arraySave.products.addAll(s);
        }catch (Exception e){
            System.out.println("Snapdeal not working");
        }
    }

    @Override
    protected ArrayList<Product> doInBackground(String... strings) {
        try{
            System.out.println("Running snapdeal on thread");
            //initialising calling.java and referencing it
            CallingGeneral calling = new CallingGeneral();
            calling.call(3,strings[0],"col-xs-6  favDp product-tuple-listing js-tuple ","a","product-title",
                    "lfloat product-price","lfloat product-desc-price strike ","source","product-discount", "product-rating-count","filled-stars");
            //initialising and referencing Calling method variables
            this.products = calling.products;
            System.out.println("Snapdeal ended");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Snapdeal not working" + e);
            return null;
        }
    }
}