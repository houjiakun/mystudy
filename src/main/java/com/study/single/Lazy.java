package com.study.single;

public class Lazy {
    private static Lazy lazy;
    private Lazy(){

    }

    public static Lazy  getLazy(){
        if(lazy==null){
            synchronized (Lazy.class) {
                lazy = new Lazy();
                System.out.println(System.currentTimeMillis() + "" + lazy);
            }
        }
        return lazy;
    }

    /**
     * 双重检验锁
     */

    public static Lazy getLazy1(){
        if(lazy==null){
            synchronized (Lazy.class) {
                if(lazy==null){
                    lazy = new Lazy();
                    System.out.println(System.currentTimeMillis() + "" + lazy);
                }
            }
        }
        return lazy;
    }

}
