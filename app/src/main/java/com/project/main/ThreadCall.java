package com.project.main;

public class ThreadCall implements Runnable {
    String url, methodCallingName;

    ThreadCall(String url, String methodCallingName){
        this.url = url;
        this.methodCallingName = methodCallingName;
    }
    @Override
    public void run() {
        if(methodCallingName.equals("Snapdeal")){
            Snapdeal snap = new Snapdeal();
            snap.execute(url);
        }
        if(methodCallingName.equals("ShopClues")){
            ShopClues shop = new ShopClues();
            shop.execute(url);
        }
        if(methodCallingName.equals("Paytm")){
            Paytm pay = new Paytm();
            pay.execute(url);
        }
        if(methodCallingName.equals("Flipkart")){
            Flipkart flip = new Flipkart();
            flip.execute(url);
        }
    }

    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
}
