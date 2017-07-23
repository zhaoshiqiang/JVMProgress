package chapter3;

/**
 * JVM提供PretenureSizeThreshold这个参数，令大于这个设置值的对象直接在老年代分配，这样做的目的是避免在Eden区及两个Survivor区之间发生大量的内存复制
 * PretenureSizeThreshold这个参数只对Serial和ParNew有效
 * VM args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728（3 * 1024 * 1024，这个参数不能直接写3MB）
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class testPretenureSizeThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        //Eden空间机会没有被使用，老年代10MB空间被使用40%，allcation直接分配在老年代中
        //因为PretenureSizeThreshold被设置为3MB，超过3MB就会直接在老年代进行分配
        byte[] allcation = new byte[ 4 * _1MB];
    }
}
