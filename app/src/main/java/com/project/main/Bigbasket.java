package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Bigbasket extends AsyncTask<String, Void, ArrayList<String>> implements arraySave {
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> tempdiscount = new ArrayList<>();
    ArrayList<String> temppricebefore = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        super.onPostExecute(s);
        arraySave.discountedprice.addAll(s);
        arraySave.discount.addAll(tempdiscount);
        arraySave.pricebefore.addAll(temppricebefore);
        arraySave.titleallproducts.addAll(temptitlestore);
        arraySave.producturl.addAll(tempurlstore);
        arraySave.imageurls.addAll(tempimageurl);

    }


    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try{
            System.out.println("Running Bigbasket on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(7,strings[0],"item prod-deck row ng-scope","a","ng-binding",
                    "discnt-price","mp-price ng-scope","img-responsive blur-up lazyautosizes lazyloaded","ng-scope");
            //initialising and referencing Calling method variables
            temptitlestore = calling.temptitlestore;
            tempimageurl = calling.tempimageurl;
            ArrayList<String> urlstore = calling.tempurlstore;
            for(String s : urlstore){
                String a = "https://bigbasket.com" + s;
                tempurlstore.add(a);
            }
            ArrayList<String> mainlist= calling.temppriceafter;
            System.out.println("Bigbasket ended");
            return mainlist;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Bigbasket not working" + e);
            return null;
        }
    }
}
