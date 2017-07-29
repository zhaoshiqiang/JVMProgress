package chapter3;

/**
 * 测试使用的是Serial / Serial Old收集器
 * VM args: -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class testAllocation {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7;
        allocation1 = new byte[ 2 * _1MB];
        allocation2 = new byte[ 2 * _1MB];
        allocation3 = new byte[ 2 * _1MB];
        allocation1 = null;
        /*
        * 出现一次Minor GC，这是因为给allocation4分配所需的2M内存时，发现Eden已经被占用7.8MB（6MB对象，其他为程序所需空间），
        * 剩余空间已经不足以分配allocation4的2MB内存，所以发生MinorGC。
        * 不过，因为allocation2，allocation3都有引用，所以这次GC总内存占用量减少了allocation1的2M（7645K->4714K）
        * GC期间JVM发现已有的2个2MB大小的对象全部无法放入Survivor空间（Survivor空间只有1MB大小）,
        * 所以只好通过担保机制提前转移到老年代了（tenured generation   total 10240K, used 4096K ）
        * */
        allocation4 = new byte[ 2 * _1MB];
        allocation5 = new byte[ 2 * _1MB];
        allocation6 = new byte[ 2 * _1MB];
        allocation5 = null;
        allocation6 = null;
        allocation2 = null;
        /*
        * 这里再出现一次Minor GC，出现的原因同上。
        * allocation5，allocation6被清空，
        * allocation4无法放入Survior区，所以通过担保机制进入老年代，此时新生代为空（7004K->1K(9216K)）
        * 虽然老年代中的allocation2已经失效，但是因为老年代的GC没有触发，
        * 所以老年代被占用6MB(tenured generation   total 10240K, used 6760K)
        *
        * */
        allocation7 = new byte[ 2 * _1MB];
        allocation7 = null;
        //经过这次System.gc()，老年代将把失效的allocation2空间删除，整理后为4MB(Tenured: 6763K->4710K(10240K))
        System.gc();
    }
}
