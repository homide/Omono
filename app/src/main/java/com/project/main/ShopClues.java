//Refer to MainActivity, Main2Activity and Calling for commenting references

package com.project.main;

import android.os.AsyncTask;

import java.util.ArrayList;

public class ShopClues extends AsyncTask<String, Void, ArrayList<String>> implements arraySave {

    //ArrayList for corresponding objects
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    String link;

    @Override
    protected void onPostExecute(ArrayList<String> s) {

        super.onPostExecute(s);
        arraySave.allproducts.addAll(s);
        arraySave.titleallproducts.addAll(temptitlestore);
        arraySave.producturl.addAll(tempurlstore);
        arraySave.imageurls.addAll(tempimageurl);

    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try{

            //initialising calling.java and referencing it
            System.out.println("Running shopclues on thread");
            Calling calling = new Calling();
            link = strings[0];
            calling.call(2,strings[0],"column col3 search_blocks", "a", "h2", "p_price", "old_prices",
                    "img", "prd_discount");
            //initialising and referencing Calling method variables
            temptitlestore = calling.temptitlestore;
            ArrayList<String> imageurl = calling.tempimageurl;
            for(int i = 0; i < imageurl.size();i++){
                String s = imageurl.get(i);
                if(i <4){
                    tempimageurl.add(s);
                }else {
                    String a = "https:" + s;
                    tempimageurl.add(a);
                }
            }
            ArrayList<String> urlstore = calling.tempurlstore;
            for(String s: urlstore){
                String a = "https:" + s;
                tempurlstore.add(a);
            }
            ArrayList<String> mainlist= calling.mainlist;
            System.out.println("Ended shopclues on thread");
            return mainlist;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("ShopClues not working" + e);
            return null;
        }
    }
}