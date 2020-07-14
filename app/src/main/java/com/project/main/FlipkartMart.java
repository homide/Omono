package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class FlipkartMart extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running FlipkartMart on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(8, link,"_3ZexUx","a","_3H3m_4","_1vC4OE _2DHlR0", "_3auQ3N",
                        "_1Nyybr  _30XEf0", "VGWI6T _2b6T6s");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended FlipkartMart");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("FlipkartMart not working" + e);
            return null;
        }
    }
}
