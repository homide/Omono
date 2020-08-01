package com.project.main;

public class ThreadCallGrocery implements Runnable {
    String url, methodCallingName;

    ThreadCallGrocery(String url, String methodCallingName){
        this.url = url;
        this.methodCallingName = methodCallingName;
    }
    @Override
    public void run() {
        if(methodCallingName.equals("Grofers")){
            Grofers grof = new Grofers();
            grof.execute(url);
        }
        if(methodCallingName.equals("Bigbasket")){
            Bigbasket big = new Bigbasket();
            big.execute(url);
        }
        if(methodCallingName.equals("AmazonPantry")){
            AmazonPantry amz = new AmazonPantry();
            amz.execute(url);
        }
        if(methodCallingName.equals("Flipmart")){
            FlipkartMart flip = new FlipkartMart();
            flip.execute(url);
        }
    }

    public void start(){
        System.out.println("Starting " + methodCallingName);
        Thread t = new Thread(this);
        t.start();
    }
}
