package chapter3;

/**
 * 引用计数法缺陷是无法回收循环引用的对象
 * JVM并没有用这种计数方法
 * -XX:+PrintGCDetails 输出GC详细信息
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class ReferenceCountingGC {
    private static final int _1MB = 1024 * 1024;
    //这个属性的唯一意义就是占内存，以便能在GC日志中看清楚是否被回收过
    private byte[] bigSize = new byte[2 * _1MB];

    public Object instance = null;

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        System.gc();
    }
}
