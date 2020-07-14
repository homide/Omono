package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Reliance extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
    //ArrayList for corresponding objects
    ArrayList<Product> products = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<Product> s) {
        super.onPostExecute(s);
        arraySave.products.addAll(s);
    }

    @Override
    protected ArrayList<Product> doInBackground(String... strings) {
        try{
            System.out.println("Running Reliance on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(19,strings[0],"sp grid","a","sp__name","sc-gqjmRU hpOGdX","sc-gqjmRU kiZgJu","img","sc-kGXeez cCDhMe sc-gqjmRU NfRgd");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended Reliance");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Reliance not working" + e);
            return null;
        }
    }
}
