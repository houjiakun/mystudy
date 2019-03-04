
import java.io.IOException;
public class Test {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(1);
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2);
        },"myThread11111").start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @org.junit.Test
    public  void test() {

    }


}
