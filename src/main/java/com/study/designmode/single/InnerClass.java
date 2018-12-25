package com.study.designmode.single;

public class InnerClass {

    private InnerClass(){

    }

    private  static class innerClassHolder{
        private static final InnerClass innerClass =new InnerClass();
    }

    private static final InnerClass getInnerClass(){
        return innerClassHolder.innerClass;
    }

    private Object readResolve() {
         return getInnerClass();
     }

}
