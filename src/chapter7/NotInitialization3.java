package chapter7;

/**
 * 被动使用类字段演示三
 * 常量在编译阶段会存于调用类的常量池，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 * 实际上NotInitialization3的Class文件中并没有ConstClass类的符号引用入口，这两个雷在编程成class之后就不存在任何联系了
 * Created by zhaoshq on 2017/7/24.
 */
public class NotInitialization3 {

    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
    }
}
