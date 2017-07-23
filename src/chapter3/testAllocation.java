package chapter3;

/**
 * 测试使用的是Serial / Serial Old收集器
 * VM args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class testAllocation {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[ 2 * _1MB];
        allocation2 = new byte[ 2 * _1MB];
        allocation3 = new byte[ 2 * _1MB];
        //出现一次Minor GC，这是因为给allocation4分配所需的4M内存时，发现Eden已经被占用6MB，剩余空间已经不足以分配allocation4的4MB内存，所以发生MinorGC。不过，因为每个对象都有引用，所以这次GC总内存占用量计划没有减少（7471K->6675K）
        //GC期间JVM发现已有的3个2MB大小的对象全部无法放入Survivor空间（Survivor空间只有1MB大小）
        //所以只好通过担保机制提前转移到老年代了
        allocation4 = new byte[ 4 * _1MB];
        //这次GC结束后，4MB的allocation4对象顺利分配到Eden中，（eden space 8192K,  53% used）
        //因此程序执行玩的结果是Eden占用4MB，Survivor空闲，老年代被占用6MB（被allocation1，allocation2，allocation3占用，total 10240K, used 6144K），通过GC日志可以看到
    }
}
