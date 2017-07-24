package chapter7;

/**
 * Created by zhaoshq on 2017/7/24.
 */
class SuperClass{

    static {
        System.out.println("SuperClass init!");
    }
    //对于静态字段，只有直接定义这个字段的类才会被初始化
    public static int value = 123;
}
