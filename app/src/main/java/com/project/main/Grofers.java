package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class Grofers extends AsyncTask<String, Void, ArrayList<String>> implements arraySave{
    //ArrayList for corresponding objects
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
            System.out.println("Running grofers on thread");
            //initialising calling.java and referencing it
            Calling calling = new Calling();
            link = strings[0];
            calling.call(6,strings[0],"product__wrapper","a","plp-product__name--box",
                    "plp-product__price--new","plp-product__price--old display--inline-block@mobile","img","plp-product__offer");
            //initialising and referencing Calling method variables
            temptitlestore = calling.temptitlestore;
            ArrayList<String> imageurl = calling.tempimageurl;
            for(String s: imageurl){
                String a = "https:" + s;
                tempimageurl.add(a);
            }
            ArrayList<String> urlstore = calling.tempurlstore;
            for(String s : urlstore){
                String a = "https://grofers.com" + s;
                tempurlstore.add(a);
            }
            ArrayList<String> mainlist= calling.temppriceafter;
            System.out.println("Grofers ended");
            return mainlist;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Grofers not working" + e);
            return null;
        }
    }
}
