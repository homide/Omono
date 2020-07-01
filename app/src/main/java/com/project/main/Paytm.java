//Refer to MainActivity, Main2Activity and Calling for commenting references

package com.project.main;

import android.os.AsyncTask;
import java.util.ArrayList;

public class Paytm extends AsyncTask<String, Void, ArrayList<String>> implements arraySave {

    //ArrayList for corresponding objects
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> temptitlestore = new ArrayList<>();
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
            Calling calling = new Calling();
            link = strings[0];
            calling.call(4,strings[0],"_3WhJ","a","UGUy","_1kMS","dQm2",
                    "img","c-ax");
            //initialising and referencing Calling method variables
            temptitlestore = calling.temptitlestore;
            ArrayList<String> urlstore = calling.tempurlstore;
            for(String s : urlstore){
                String a = "https://paytmmall.com" + s;
                tempurlstore.add(a);
            }
            tempimageurl = calling.tempimageurl;
            ArrayList<String> mainlist= calling.mainlist;
            return mainlist;
        }catch (Exception e){
            //fail-safe :)
            System.out.println("Paytm not working" + e);
            return null;
        }
    }
}