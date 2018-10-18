package com.study.factory;

import java.io.File;

public class Abstractfactory {
    Fruit fruit = null;
    public void createApple(){
        fruit= new Apple();
        fruit.getName();
    }

    public void createOrange(){
        fruit= new Orange();
        fruit.getName();
    }

    public static void main(String[] args) {
        Abstractfactory abstractfactory = new Abstractfactory();
        abstractfactory.createApple();
        abstractfactory.createOrange();


    }
}
