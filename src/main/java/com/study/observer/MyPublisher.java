package com.study.observer;


import java.util.Observable;

public class MyPublisher extends Observable {
    private String name;
    private Integer age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void operate(){
        System.out.println("1");
        this.setChanged();
        this.notifyObservers("java");
    }
    public static void main(String[] args) {
        MyPublisher publisher = new MyPublisher();
        publisher.setName("mic");
        publisher.addObserver((Observable o, Object value) ->{
            MyPublisher publish = (MyPublisher)o;
            System.out.println("第一个observer发布的内容为" + publish.getName());
        } );

        publisher.addObserver((Observable o, Object value) ->{
            System.out.println("第二个observer发布的内容为" + value);
        } );
        publisher.operate();
    }


}
