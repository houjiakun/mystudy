package com.study.clonetest;

import java.io.Serializable;

public class Employee implements  Cloneable,Serializable{
    private String code;
    private String name;

    public Employee(String code, String name){
        this.code = code;
        this.name = name;
    }
    @Override
    public Object clone(){
        Employee employee=null;
        try {
            employee = (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employee;
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
