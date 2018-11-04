import java.lang.reflect.Field;

public class IntegerTest {

    public static void main(String[] args) throws Exception {
        Integer a=1,b=2;
        swap(a, b);
        System.out.printf("after->a:%s,b:%s", a, b);
    }

    private static void swap(Integer a, Integer b) throws NoSuchFieldException, IllegalAccessException {
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        Integer tem = new Integer(a.intValue());
        value.set(a, b.intValue());
        value.set(b, tem.intValue());
    }


}
