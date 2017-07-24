package chapter7;

/**
 * 被动使用类字段演示二
 * 通过数组定义来引用类，不会触发此类的初始化
 * Created by zhaoshq on 2017/7/24.
 */
public class NotInitialization2 {
    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[2];
        System.out.println(sca.length);
    }
}

