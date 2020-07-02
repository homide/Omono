package com.project.main;

public class CallingGrocery {
    public void callinggrocery(String searchtext) {
        ThreadCall grof = new ThreadCall("https://grofers.com/s/?q="+ searchtext+ "&suggestion_type=0&t=1", "Grofers");
        ThreadCall shop = new ThreadCall("https://www.shopclues.com/search?q=" + searchtext + "&sc_z=2222&z=0&count=10", "ShopClues");
        ThreadCall pay = new ThreadCall("https://www.paytmmall.com/shop/search?q=" + searchtext + "&from=organic&child_site_id=6", "Paytm");
        ThreadCall flip = new ThreadCall("https://www.flipkart.com/search?q=" + searchtext + "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off", "Flipkart");
        grof.start();
    }
}
