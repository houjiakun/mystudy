

import java.io.IOException;
import java.util.concurrent.*;

public class Test {
    private static Object obj = new Object();

    public static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static class MyThread implements Callable {
        private Object flag;
        public MyThread(Object flag){
            this.flag=flag;
        }
        @Override
        public Object call() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1/0);
            return "1";
        }
    }

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static volatile Boolean flag = false;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Object flag = new Object();
        MyThread myThread = new MyThread(flag);
        Future submit = executorService.submit(myThread);
        System.out.println(submit.get());
        System.out.println(1);
        Thread.currentThread().sleep(5000);
        System.out.println(flag);
        System.out.println(2);

    }

    @org.junit.Test
    public  void test() {

    }


}
