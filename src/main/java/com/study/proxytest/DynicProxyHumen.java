package com.study.proxytest;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynicProxyHumen implements InvocationHandler,Serializable{

    private Object target;

    public DynicProxyHumen(Object target){
        this.target = target;
    }
    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        proxyWarmup();
        Object o = method.invoke(target, args);
        return o;
    }

    public void proxyWarmup(){
        System.out.println("warmup");
    }

}
