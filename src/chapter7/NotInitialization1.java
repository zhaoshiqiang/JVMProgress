package chapter7;

/**
 * 被动使用类字段演示一
 * 通过子类引用父类的静态字段，不会导致子类初始化
 * -XX:+TraceClassLoading 参数观察到此操作类加载情况
 * Created by zhaoshq on 2017/7/24.
 */
public class NotInitialization1 {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}

