import java.util.concurrent.CountDownLatch;

public class Test {
    private static volatile int count = 0;
    private static final int times = 10000;

    public static void main(String[] args) {

        long curTime = System.nanoTime();

        Thread decThread = new DecThread();
        decThread.start();

        System.out.println("Start thread: " + Thread.currentThread() + " i++");

        for (int i = 0; i < times; i++)
                count = count+1;
        System.out.println("End thread: " + Thread.currentThread() + " i--");

        // 等待decThread结束
        while (decThread.isAlive())
            ;

        long duration = System.nanoTime() - curTime;
        System.out.println("Result: " + count);
        System.out.format("Duration: %.2fs\n", duration / 1.0e9);
    }

    private static class DecThread extends Thread {

        @Override
        public void run() {
            System.out.println("Start thread: " + Thread.currentThread()
                    + " i--");
            for (int i = 0; i < times; i++) {
                count = count-1;
            }
            System.out
                    .println("End thread: " + Thread.currentThread() + " i--");
        }
    }
}
