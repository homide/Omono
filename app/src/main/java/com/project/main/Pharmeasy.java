package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Pharmeasy extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Pharmeasy on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(14,strings[0],"_3o0NT _1NxW8" , "a", "ooufh","_1_yM9", "_3FUtb" , "img","_306Fp" );
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended Pharmeasy");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Pharmeasy not working" + e);
            return null;
        }
    }
}
