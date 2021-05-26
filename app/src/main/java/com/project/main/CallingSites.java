package com.project.main;

import android.content.res.Resources;

import java.util.concurrent.ThreadLocalRandom;

public class CallingSites {

    public void callingmain(String searchtext){
        ThreadCallGeneral snap = new ThreadCallGeneral("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal");
        ThreadCallGeneral shop = new ThreadCallGeneral("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
        ThreadCallGeneral pay = new ThreadCallGeneral("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
        ThreadCallGeneral flip = new ThreadCallGeneral("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
        ThreadCallGeneral amz = new ThreadCallGeneral("https://www.amazon.in/s?k="+searchtext+"&ref=nb_sb_noss_2","Amazon");
        snap.start();
        shop.start();
        pay.start();
//        flip.start();
        amz.start();
    }

    public void callingfashion(String searchtext){

        ThreadCallFashion pay = new ThreadCallFashion("https://www.paytmmall.com/shop/search?q=" +searchtext+ "&from=organic&child_site_id=6","Paytm");
        pay.start();

        ThreadCallFashion snap = new ThreadCallFashion("https://www.snapdeal.com/search?keyword=" + searchtext + "&santizedKeyword=&catId=" +
                "&categoryId=0&suggested=true&vertical=&noOfResults=20&searchState=&clickSrc=suggested&lastKeyword=&prodCatId=&change" +
                "BackToAll=false&foundInAll=false&categoryIdSearched=&cityPageUrl=&categoryUrl=&url=&utmContent=&dealDetail=&sort=rlvncy","Snapdeal" );
        snap.start();

        ThreadCallFashion shop = new ThreadCallFashion("https://www.shopclues.com/search?q=" +searchtext +"&sc_z=2222&z=0&count=10", "ShopClues");
        shop.start();

        ThreadCallFashion flip = new ThreadCallFashion("https://www.flipkart.com/search?q=" + searchtext+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
        flip.start();

        ThreadCallFashion amz = new ThreadCallFashion("https://www.amazon.in/s?k="+searchtext+"&ref=nb_sb_noss_2","Amazon");
        amz.start();

        ThreadCallFashion ajio = new ThreadCallFashion("https://www.ajio.com/search/?text=" + searchtext, "AJIO");
        ajio.start();

        ThreadCallFashion myn = new ThreadCallFashion("https://www.myntra.com/" + searchtext, "Myntra");
        myn.start();

        ThreadCallFashion koov = new ThreadCallFashion("https://www.koovs.com/" + searchtext, "Koovs");
        koov.start();

        ThreadCallFashion bew = new ThreadCallFashion("https://www.bewakoof.com/search/" + searchtext + "?ga_q=" + searchtext, "Bewakoof");
        bew.start();
    }

    public void callingmedicines(String searchtext){
        ThreadCallMedicines phar = new ThreadCallMedicines("https://pharmeasy.in/search/all?name=" + searchtext, "Pharmeasy");
        phar.start();

        ThreadCallMedicines netmed = new ThreadCallMedicines("https://www.netmeds.com/catalogsearch/result?q=" + searchtext, "Netmeds");
        netmed.start();

        ThreadCallMedicines mg1 = new ThreadCallMedicines("https://www.1mg.com/search/all?name=" + searchtext, "1mg");
        mg1.start();
    }

    public void callinggrocery(String searchtext){

        ThreadCallGrocery grof = new ThreadCallGrocery("https://grofers.com/s/?q="+ searchtext+ "&suggestion_type=0&t=1", "Grofers");
        grof.start();

        ThreadCallGrocery amzpantry = new ThreadCallGrocery("https://www.amazon.in/s?k="+searchtext+"&i=pantry&srs=9574332031&ref=nb_sb_noss", "AmazonPantry");
        amzpantry.start();

        ThreadCallGrocery flip = new ThreadCallGrocery("https://www.flipkart.com/search?q="+searchtext+"&otracker=search&otracker1=search&marketplace=GROCERY&as-show=on&as=off", "Flipmart");
        flip.start();

        ThreadCallGrocery big = new ThreadCallGrocery("https://www.bigbasket.com/ps/?q=" + searchtext, "Bigbasket");
        big.start();

    }

    public void callingelectronics(String seachtext){
        ThreadCallElectronics croma = new ThreadCallElectronics("https://www.croma.com/search/?text="+ seachtext, "Croma");
        croma.start();
//
        ThreadCallElectronics rel = new ThreadCallElectronics("https://www.reliancedigital.in/search?q="+seachtext+":relevance", "Reliance");
        rel.start();
//
        ThreadCallElectronics tatacliq = new ThreadCallElectronics("https://www.tatacliq.com/search/?searchCategory=all&text=" + seachtext, "Tatacliq");
        tatacliq.start();
    }
}
