package com.study.designmode.proxytest;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {

    public static void main(String[] args) throws Exception{
   /*     Humen humen = new HumenSonImpl();
        Humen o = new DynicProxyHumen(humen).getProxy();
        o.eat();
        FileOutputStream os = new FileOutputStream("D:\\humen.data");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(humen);
        oos.flush();
        oos.close();
        */

        /*  Humen humen = new HumenSonImpl();
        Humen o = new DynicProxyHumen(humen).getProxy();
        // 根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass("DynicProxyHumen", HumenSonImpl.class.getInterfaces());
        String paths = HumenSonImpl.class.getResource(".").getPath();
        System.out.println(paths);
        FileOutputStream out = null;

        try {
            //保留到硬盘中
            out = new FileOutputStream(paths + "DynicProxyHumen" + ".class");
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        //该设置用于输出cglib动态代理产生的类
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\class");
        //cglibproxy
        HumenSonImpl bookFacade=new HumenSonImpl();
        CglibProxy  cglib = new CglibProxy();
        HumenSonImpl bookCglib=(HumenSonImpl)cglib.getInstance(bookFacade);
        bookCglib.eat();


    }




}
