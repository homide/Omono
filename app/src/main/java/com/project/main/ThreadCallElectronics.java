package com.project.main;

public class ThreadCallElectronics implements Runnable {
    String url, methodCallingName;

    ThreadCallElectronics(String url, String methodCallingName){
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
        if(methodCallingName.equals("Amazon")){
            Amazon amz = new Amazon();
            amz.execute(url);
        }
        if(methodCallingName.equals("Croma")){
            Croma cro = new Croma();
            cro.execute(url);
        }
        if(methodCallingName.equals("Tatacliq")){
            Tatacliq tc = new Tatacliq();
            tc.execute(url);
        }
        if(methodCallingName.equals("Reliance")){
            Reliance rel = new Reliance();
            rel.execute(url);
        }
    }

    public void start(){
        System.out.println("Starting " + methodCallingName);
        Thread t = new Thread(this);
        t.start();
    }
}
