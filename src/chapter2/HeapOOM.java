package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * java堆内存溢出异常测试，将-Xmx与Xmx设置的一样可避免堆自动扩展
 * VM args: -verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by zhaoshiqiang on 2017/7/22.
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }

    static class OOMObject{}
}
