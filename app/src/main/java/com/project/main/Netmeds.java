package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Netmeds extends AsyncTask<String, Void, ArrayList<Product>> implements arraySave {
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
            System.out.println("Running Netmeds on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(16,strings[0],"ais-InfiniteHits-item", "a", "info", "final-price", "striken_text", "img", "disc-price");
            //initialising and referencing Calling method variables
            products = calling.products;
            System.out.println("Ended Netmeds");
            return products;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Netmeds not working" + e);
            return null;
        }
    }
}
