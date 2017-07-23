package chapter3;

/**
 * 动态对象年龄判定
 * Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半，年龄 >= 该年龄的对象就直接进入老年代，不受MaxTenuringThreshold的约束。
 * VM args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class testTenuringThreshold2 {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        //allocation1 + allocation2 大于 survivor空间一半
        allocation3 = new byte[ 4 * _1MB];
        allocation4 = new byte[ 4 * _1MB];
        allocation4 = null;
        //这里会发生一次gc
        allocation4 = new byte[ 4 * _1MB];
    }
}
