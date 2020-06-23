package com.project.main;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Snapdeal extends AsyncTask<String, Void, ArrayList<String>> {
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
        for (int j = 0; j < 6; j++) {
            product = s.get(j);
            title = temptitlestore.get(j);
            titleallproducts.add(title);
            urlstore = tempurlstore.get(j);
            allproducts.add(product);
            producturl.add(urlstore);
            imagelink = tempimageurl.get(j);
            imageurls.add(imagelink);
        }
        String seemore = "SHOW MORE PRODUCTS ON SNAPDEAL......";
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
        try {
            Document doc = Jsoup.connect(strings[0]).get();
            Elements links = doc.getElementsByClass("col-xs-6  favDp product-tuple-listing js-tuple ");
            ArrayList<String> mainlist = new ArrayList<String>();
            link = strings[0];
            String title = "SNAPDEAL PRODUCTS";
            String url = "https://www.snapdeal.com";
            String imageurl = "https://www.apkmirror.com/wp-content/uploads/2018/12/5c0671b80df76.png";
            temptitlestore.add(title);
            tempurlstore.add(url);
            tempimageurl.add(imageurl);
            mainlist.add("");


            for (Element link : links) {
                String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null, temp6 = null;
                String permanent1 = null;

                Elements elLink = link.getElementsByTag("a");

                Elements eltitle = link.getElementsByClass("product-title"); //for product title

                Elements elpricebefore = link.getElementsByClass("lfloat product-desc-price strike ");

                Elements elpriceafter = link.getElementsByClass("lfloat product-price");

                Elements elproductimage = link.getElementsByClass("product-image ");

                Elements discount = link.getElementsByClass("product-discount");


                //product title loop
                for (Element titleOfProduct : eltitle) {
                    temp1 = titleOfProduct.text();
                }

                //product original price loop
                for (Element priceOfProductBefore : elpricebefore) {
                    temp2 = "Price before: " + priceOfProductBefore.text();
                }

                //product discounted price loop
                for (Element priceOfProductAfter : elpriceafter) {
                    temp3 = "Discounted price: " + priceOfProductAfter.text();
                }

                //discount in number loop
                for (Element productdiscount : discount) {
                    temp4 = "Discount: " + productdiscount.text();
                }

                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("src");
                }

                ArrayList<String> linkArray = new ArrayList<String>();
                for (Element elementLink : elLink) {
                    String MainLink = elementLink.attr("href");
                    linkArray.add(MainLink);
                }

                for (int j = 0; j < 1; j++) {
                    temp5 = linkArray.get(0);
                }

                if (elpricebefore.text()==null)
                {
                    permanent1 = "\n"+ "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                }

                else
                {
                    permanent1 ="\n" + temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

                }
                temptitlestore.add(temp1);
                mainlist.add(permanent1);
                tempurlstore.add(temp5);
                tempimageurl.add(temp6);

            }
            return mainlist;
        } catch (Exception e) {
            ArrayList<String> exception = new ArrayList<String>();
            String ex = e.toString();
            exception.add(ex);
            return exception;
        }
    }
}
