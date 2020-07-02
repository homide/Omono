//Refer to MainActivity and Main2Activity for commenting references

package com.project.main;

import org.jsoup.Jsoup; //JSoup Library to Parse HTML
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Calling {

    //ArrayLists to store Objects
    ArrayList<String> temptitlestore = new ArrayList<>();
    ArrayList<String> tempurlstore = new ArrayList<>();
    ArrayList<String> tempimageurl = new ArrayList<>();
    ArrayList<String> tempdiscount = new ArrayList<String>();
    ArrayList<String> temppricebefore = new ArrayList<>();
    ArrayList<String> temppriceafter = new ArrayList<>();
    String link;


    public void call(int sNo,String websiteUrl,String productClass, String tagForLink, String titleClass, String discountedPriceClass, String originalPriceClass, String productImageClass,String productDiscount) throws IOException {

        Document doc = Jsoup.connect(websiteUrl).get(); //JSoup Element to get HTML Data from the corresponding website
        Elements links = doc.getElementsByClass(productClass); //JSoup Element to get HTML Data from the corresponding Class/Section
        link = websiteUrl;

        // 1- Flipkart
        // 2- Shopclues
        // 3- Snapdeal
        // 4- Paytm

        //Loop To Initialize Variables for storing required HTML Data
        int i = 0;
        for (Element link : links) {
            String temp1 = null, temp2 = null, temp3 = null, temp4 = null, temp5 = null, temp6 = null;
            Elements eltitle, elproductimage;

            Elements elLink = link.getElementsByTag(tagForLink); //JSoup Element to get URL of the product

            if(sNo == 2){                                        //When ShopClues is being parsed,
                eltitle = link.getElementsByTag(titleClass);     //We are getting Title of Product using HTML Tag because there is no proper class identifying the title
            }else{                                               //When any other website is being parsed,
                eltitle = link.getElementsByClass(titleClass);   //We are getting Title of Product using HTML Class because there is class identifying the title
            }

            Elements elpricebefore = link.getElementsByClass(originalPriceClass); //JSoup Element to get Original Price Data from corresponding HTML Class

            Elements elpriceafter = link.getElementsByClass(discountedPriceClass); //JSoup Element to get Discounted Price Date from corresponding HTML Class

            if(sNo == 2 ||sNo == 3|| sNo == 4|| sNo == 6){                                         //When ShopClues and Paytm mall is being parsed,
                elproductimage = link.getElementsByTag(productImageClass);    //We are getting URL of Product Image by HTML Tag because there is no proper class identifying the image url
            }
            else {                                                           //When any other website is being Parsed,
                elproductimage = link.getElementsByClass(productImageClass);  //We are getting URL of Product image by HTML Class because there is a proper class identifying the image url
            }

            Elements discount = link.getElementsByClass(productDiscount);  //JSoup element to parse Discount data from corresponding classes


            //product title loop
            for (Element titleOfProduct : eltitle) {
                if(titleOfProduct == null){
                    temp1 = "";
                }
                else{
                    temp1 = titleOfProduct.text();
                }
            }

            //product original price loop
            for (Element priceOfProductBefore : elpricebefore) {
                if(priceOfProductBefore == null){
                    temp2 = "";
                }
                else {
                    temp2 = "Price before: " + priceOfProductBefore.text();
                }
            }

            //product discounted price loop
            for (Element priceOfProductAfter : elpriceafter) {
                if(priceOfProductAfter == null){
                    temp3 = "";
                }
                else {
                    temp3 = "Discounted price: " + priceOfProductAfter.text();
                }
            }

            //discount in number loop
            for (Element productdiscount : discount) {
                if(productDiscount == null){
                    temp4 = "";
                }
                else {
                    temp4 = "Discount: " + productdiscount.text();
                }
            }

            //image loop
            if(sNo == 3){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("srcset");
                    break;
                }
            }
            if(sNo == 2){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("data-img");
                }
            }
            if(sNo == 1 || sNo == 4 ){
                for(Element elimage : elproductimage){
                    temp6 = elimage.attr("src");
                }
            }
            if(sNo == 6){
                for(Element elimage: elproductimage){
                    temp6 = elimage.absUrl("src");
                }
            }

            //produuct URL loop
            ArrayList<String> linkArray = new ArrayList<String>();
            for (Element elementLink : elLink) {
                String MainLink = elementLink.attr("href");
                linkArray.add(MainLink);
            }

            //URL storing
            for (int j = 0; j < 1; j++) {
                temp5 = linkArray.get(0);
            }

            temptitlestore.add(temp1);
            temppricebefore.add(temp2);
            temppriceafter.add(temp3);
            tempdiscount.add(temp4);
            tempurlstore.add(temp5);
            tempimageurl.add(temp6);

        }
    }
}
