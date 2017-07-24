package chapter7;

/**
 * Created by zhaoshq on 2017/7/24.
 */
public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLOWORLD = "hello world";
}
