package com.study.designmode.observer;


import java.beans.PropertyChangeSupport;

public class EventListenerDemo {
    public static void main(String[] args) {
        Person person = new Person();
        PropertyChangeSupport support = new PropertyChangeSupport(person);
        support.addPropertyChangeListener("name",evt->{
            Person bean  = (Person) evt.getSource();
            System.out.printf("person[%S]的name 老值[%s],新值[%s]", bean , evt.getOldValue(), evt.getNewValue());
        } );
        support.firePropertyChange("name", null, "MIC");
    }
    public static class Person{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}
