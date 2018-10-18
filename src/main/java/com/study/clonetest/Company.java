package com.study.clonetest;

import com.study.common.Reflections;

import java.io.*;
import java.lang.reflect.Field;

public class Company implements  Cloneable, Serializable{
    private String code;
    private String name;

    @DeepClone
    private Employee employee;
    @DeepClone
    private Desk desk;

    public Company(String code, String name, Employee employee, Desk desk){
        this.code = code;
        this.name = name;
        this.employee = employee;
        this.desk = desk;
    }

    @Override
    public Object clone(){
        Company company=null;
        try {
            company = (Company) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return company;
    }


    public Object deepClone1(){
        Company company=null;
        try {
            company = (Company) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Field[] fields = Company.class.getDeclaredFields();
        for (Field field : fields) {
            DeepClone deepClone = field.getAnnotation(DeepClone.class);
            if(deepClone!=null) {
                try {
                    Object o = field.get(company);
                    Object o1 = Reflections.invokeMethodByName(o, "clone",null);
                    Reflections.setFieldValue(company, field.getName() , o1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return company;
    }

    public Object deepClone(){
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois =new ObjectInputStream(bis);
            return ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
    }

    public static void main(String[] args) {
        Employee employee =new Employee("hjk","侯佳坤");
        Desk desk =new Desk("zhuozi","桌子");
        Company c1=new Company("yida","易达", employee, desk);

        Company c2 = (Company) c1.clone();
        System.out.println(c2.getDesk().getCode());
        c1.getDesk().setCode("zhuozi1");
        System.out.println(c2.getDesk().getCode());

        Company c3 = (Company) c1.deepClone();
        System.out.println(c3.getDesk().getCode());
        c1.getDesk().setCode("zhuozi2");
        System.out.println(c3.getDesk().getCode());

        Company c4 = (Company) c1.deepClone1();
        System.out.println(c4.getDesk().getCode());
        c1.getDesk().setCode("zhuozi3");
        System.out.println(c4.getDesk().getCode());

    }
}
