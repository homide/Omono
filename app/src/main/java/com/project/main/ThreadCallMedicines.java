package com.project.main;

public class ThreadCallMedicines implements Runnable {
    String url, methodCallingName;

    ThreadCallMedicines(String url, String methodCallingName){
        this.url = url;
        this.methodCallingName = methodCallingName;
    }
    @Override
    public void run() {
        if(methodCallingName.equals("Pharmeasy")){
            Pharmeasy phr = new Pharmeasy();
            phr.execute(url);
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
