package chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 本机直接内存通过-XX:MaxDirectMemorySize设置，如果不指定，则默认使用java堆最大值（-Xmx）
 * VM args: -verbose:gc -XX:MaxDirectMemorySize=10M -Xmx20M
 * Created by zhaoshiqiang on 2017/7/22.
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB);
        }
    }
}
