package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class ShopClues extends AsyncTask<String, Void, ArrayList<String>> {
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>(); // all products combine
    ArrayList<String> producturl = new ArrayList<String>();
    ArrayList<String> imageurls = new ArrayList<String>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<String> s) {
        String product, urlstore,title,imagelink;

        super.onPostExecute(s);
        for (int j = 0; j < 5; j++) {
            product = s.get(j);
            title = temptitlestore.get(j);
            titleallproducts.add(title);
            urlstore = tempurlstore.get(j);
            allproducts.add(product);
            producturl.add(urlstore);
            imagelink = tempimageurl.get(j);
            imageurls.add(imagelink);
        }
        String seemore = "SHOW MORE PRODUCTS ON SHOPCLUES......";
        String seemoreimage = "https://previews.123rf.com/images/brisker/brisker1605/brisker160500027/58489983-16-icons-of-different-products-categories-for-online-shops.jpg";
        allproducts.add("");
        titleallproducts.add(seemore);
        producturl.add(link);
        imageurls.add(seemoreimage);

    }

    public ArrayList<ArrayList> arrayReturn(){

        ArrayList<ArrayList> containsAllArray = new ArrayList<>();
        containsAllArray.add(titleallproducts);
        containsAllArray.add(allproducts);
        containsAllArray.add(producturl);
        containsAllArray.add(imageurls);
        return containsAllArray;
    }


    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try{
            CallingType2 callingType2 = new CallingType2();
            link = strings[0];
            callingType2.call(strings[0],"column col3 search_blocks", "a", "h2", "p_price", "old_prices", "img", "prd_discount");
            temptitlestore = callingType2.temptitlestore;
            tempimageurl = callingType2.tempimageurl;
            tempurlstore = callingType2.tempurlstore;
            ArrayList<String> mainlist= callingType2.mainlist;
            return mainlist;
        }catch (Exception e){
            System.out.println("ShopClues not working" + e);
            return null;
        }
    }
}