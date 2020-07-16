package com.project.main;

import android.content.res.Resources;

import java.util.concurrent.ThreadLocalRandom;

public class CallingMain {

    public void callingmain(String searchtext){
        ThreadCall snap = new ThreadCall("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal");
        ThreadCall shop = new ThreadCall("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
        ThreadCall pay = new ThreadCall("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
        ThreadCall amz = new ThreadCall("https://www.amazon.in/s?k="+searchtext+"&ref=nb_sb_noss_2","Amazon");
//        snap.start();
//        shop.start();
//        pay.start();
//        flip.start();
        amz.start();
    }

    public void callingfashion(String searchtext){

//        ThreadCall pay = new ThreadCall("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
//        pay.start();
//
//        ThreadCall snap = new ThreadCall("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
//                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
//                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal" );
//        snap.start();
//
//        ThreadCall shop = new ThreadCall("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
//        shop.start();
//
//        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
//        flip.start();
//
//        ThreadCall amz = new ThreadCall("https://www.amazon.in/s?k="+searchtext+"&ref=nb_sb_noss_2","Amazon");
//        amz.start();

        ThreadCall ajio = new ThreadCall("https://www.ajio.com/search/?text=" + searchtext, "AJIO");
        ajio.start();

        ThreadCall myn = new ThreadCall("https://www.myntra.com/" + searchtext, "Myntra");
//        myn.start();

//        ThreadCall koov = new ThreadCall("https://www.koovs.com/" + searchtext, "Koovs");
//        koov.start();
//
//        ThreadCall bew = new ThreadCall("https://www.bewakoof.com/search/" + searchtext + "?ga_q=" + searchtext, "Bewakoof");
//        bew.start();
    }

    public void callingmedicines(String searchtext){
        ThreadCall phar = new ThreadCall("https://pharmeasy.in/search/all?name=" + searchtext, "Pharmeasy");
        phar.start();

        ThreadCall netmed = new ThreadCall("https://www.netmeds.com/catalogsearch/result?q=" + searchtext, "Netmeds");
        netmed.start();

        ThreadCall mg1 = new ThreadCall("https://www.1mg.com/search/all?name=" + searchtext, "1mg");
        mg1.start();
    }

    public void callinggrocery(String searchtext){

        ThreadCall grof = new ThreadCall("https://grofers.com/s/?q="+ searchtext+ "&suggestion_type=0&t=1", "Grofers");
        grof.start();

        ThreadCall amzpantry = new ThreadCall("https://www.amazon.in/s?k="+searchtext+"&i=pantry&srs=9574332031&ref=nb_sb_noss", "AmazonPantry");
        amzpantry.start();

        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q="+searchtext+"&otracker=search&otracker1=search&marketplace=GROCERY&as-show=on&as=off", "Flipmart");
        flip.start();

        ThreadCall big = new ThreadCall("https://www.bigbasket.com/ps/?q=" + searchtext, "Bigbasket");
        big.start();

    }

    public void callingelectronics(String seachtext){
//        ThreadCall croma = new ThreadCall("https://www.croma.com/search/?text="+ seachtext, "Croma");
//        croma.start();
//
        ThreadCall rel = new ThreadCall("https://www.reliancedigital.in/search?q="+seachtext+":relevance", "Reliance");
        rel.start();
//
//        ThreadCall tatacliq = new ThreadCall("https://www.tatacliq.com/search/?searchCategory=all&text=" + seachtext, "Tatacliq");
//        tatacliq.start();
    }
}
