package com.project.main;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ShopClues extends AsyncTask<String, Void, ArrayList<String>> {
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> titleallproducts = new ArrayList<String>();
    ArrayList<String> allproducts = new ArrayList<String>();
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
        try {
            Document doc = Jsoup.connect(strings[0]).get();
            Elements links = doc.getElementsByClass("column col3 search_blocks");
            ArrayList<String> mainlist = new ArrayList<String>();
            link = strings[0];
            String title = "SHOPCLUES PRODUCTS";
            String url = "https://www.shopclues.com";
            String imageurl = "https://2.bp.blogspot.com/-PhEOWIZ5RUc/WszElAqJseI/AAAAAAAAAj8/QSn5SborV2wuzVsYTCm7SJk7aoH08OQ2ACPcBGAYYCw/w680/shopclue.png";
            temptitlestore.add(title);
            tempurlstore.add(url);
            tempimageurl.add(imageurl);
            mainlist.add("");


            for (Element link : links) {
                String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null,temp6 = null;
                String permanent1 = null;

                Elements elLink = link.getElementsByTag("a");

                Elements eltitle = link.getElementsByTag("h2"); //for product title

                Elements elpricebefore = link.getElementsByClass("old_prices");

                Elements elpriceafter = link.getElementsByClass("p_price");

                Elements elproductimage = link.getElementsByTag("img");

                Elements discount = link.getElementsByClass("prd_discount");


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
                    temp5 = "https:" + linkArray.get(0);
                }

                if (elpricebefore.text()==null)
                {
                    permanent1 = "Price :" + elpriceafter.text() + "\n" + temp4 + "\n";
                }

                else
                {
                    permanent1 =temp2 + "\n" + temp3 + "\n" + temp4 + "\n";

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