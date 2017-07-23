package chapter3;

/**
 * 长期存活的对象进入老年代
 * 通过MaxTenuringThreshold来设置
 * VM args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class testTenuringThreshold {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        //什么时候进入老年代取决于-XX:MaxTenuringThreshold的设置
        allocation2 = new byte[ 4 * _1MB];
        allocation3 = new byte[ 4 * _1MB];
        allocation3 = null;
        //这里会发生一次gc
        allocation3 = new byte[ 4 * _1MB];

    }
}
