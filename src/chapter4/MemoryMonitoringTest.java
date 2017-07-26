package chapter4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JConsole内存监控练习
 * VM args: -verbose:gc -Xms100M -Xmx100M -XX:+UseSerialGC
 * Created by zhaoshq on 2017/7/26.
 */
public class MemoryMonitoringTest {

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //稍作延时，令监控曲线变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
    //一个OOMObject大约64KB
    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        int read = System.in.read();
        System.out.println("start!");
        fillHeap(1000);
    }

}
