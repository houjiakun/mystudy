package com.study.designmode.proxytest;


public class ProxyHumen implements Humen {

    public Humen humen;

    public ProxyHumen(){
        this.humen = new HumenImpl();
    }

    @Override
    public String run() {
        proxyWarmup();
        return humen.run();
    }

    @Override
    public String eat() {
        return null;
    }

    public void proxyWarmup(){
        System.out.println("warmup");
    }

    public static void main(String[] args) {
        Humen humen = new ProxyHumen();
        humen.run();
    }
}
