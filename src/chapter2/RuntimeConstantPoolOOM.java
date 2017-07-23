package chapter2;

/**
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M 来设置方法区大小
 * jdk1.7 及其之后版本中String.intern()返回引用测试
 * Created by zhaoshiqiang on 2017/7/22.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        /*
        * jdk1.7之后intern()的实现不再是复制实例，只是在常量池中记录首次出现的实力引用
        * 所以是返回true，false
        * 这样就不能通过一直String.valueof(i++).intern()来查看运行时常量池溢出异常
        * 但是可以通过CGLib这类字节码技术不断动态生成class来使方法区溢出
        * */
        String s1 = new StringBuffer("jisuanji").append("ruanjian").toString();
        System.out.println(s1.intern() == s1);

        String s2 = new StringBuffer("jisuanji").append("ruanjian").toString();
        System.out.println(s2.intern() == s2);

    }
}
