import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        Map map = new TreeMap();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(3, "2.5");


    }

    @org.junit.Test
    public  void test() {
        Map map = new HashMap(19);
        map.put("1","1");
        ConcurrentHashMap map1 = new ConcurrentHashMap<>(19);
        map1.put()
    }


}
