package com.study.clonetest;

import java.io.Serializable;

public class Desk implements  Cloneable,Serializable{
    private String code;
    private String name;

    public Desk(String code, String name){
        this.code = code;
        this.name = name;
    }
    @Override
    public Object clone(){
        Desk desk=null;
        try {
            desk = (Desk) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return desk;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
