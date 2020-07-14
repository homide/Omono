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
        if(methodCallingName.equals("Grofers")){
            Grofers grof = new Grofers();
            grof.execute(url);
        }
        if(methodCallingName.equals("Bigbasket")){
            Bigbasket big = new Bigbasket();
            big.execute(url);
        }
        if(methodCallingName.equals("Amazon")){
            Amazon amz = new Amazon();
            amz.execute(url);
        }
        if(methodCallingName.equals("AJIO")){
            Ajio ajio = new Ajio();
            ajio.execute(url);
        }
        if(methodCallingName.equals("Myntra")){
            Myntra myn = new Myntra();
            myn.execute(url);
        }
        if(methodCallingName.equals("Koovs")){
            Koovs koov = new Koovs();
            koov.execute(url);
        }
        if(methodCallingName.equals("Bewakoof")){
            Bewakoof bew = new Bewakoof();
            bew.execute(url);
        }
        if(methodCallingName.equals("Pharmeasy")){
            Pharmeasy phr = new Pharmeasy();
            phr.execute(url);
        }
        if(methodCallingName.equals("AmazonPantry")){
            AmazonPantry amz = new AmazonPantry();
            amz.execute(url);
        }
        if(methodCallingName.equals("Flipmart")){
            FlipkartMart flip = new FlipkartMart();
            flip.execute(url);
        }
        if(methodCallingName.equals("Netmeds")){
            Netmeds net = new Netmeds();
            net.execute(url);
        }
        if(methodCallingName.equals("1mg")){
            mg1 mg = new mg1();
            mg.execute(url);
        }
    }

    public void start(){
        System.out.println("Starting " + methodCallingName);
        Thread t = new Thread(this);
        t.start();
    }
}
