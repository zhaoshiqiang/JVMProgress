package chapter7;

/**
 * 非法向前引用变量
 * 初始化阶段是执行类构造器<clinit>()方法的过程，<clinit>()方法是有编译器自动产生的
 * <clinit>()方法 = 类属性的赋值动作 + static{}块
 * <clinit>()方法中的代码顺序是语句在源文件中出现的顺序决定的
 * static{}中只能访问定义在static{}之前的变量，定义在它之后的变量，static{}只能赋值，不能访问
 * Created by zhaoshq on 2017/7/24.
 */
public class TestClinit1 {

    static {
        i = 0;  //给变量赋值可以正常通过
//        System.out.println(i);  //访问变量时，会提示“非法向前引用”
    }

    static int i=1;
}
