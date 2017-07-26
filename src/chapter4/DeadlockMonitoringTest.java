package chapter4;

/**
 * JConsole死锁监控练习
 * Created by zhaoshq on 2017/7/26.
 */
public class DeadlockMonitoringTest {

    static class SynAddRunable implements Runnable{
        int a,b;

        public SynAddRunable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            //造成死锁的原因是Integer.valueOf基于减少对象创建次数和节省内存的考虑，[-128,127]之间的数字会被缓存
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a + b);
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
        * for循环是可省略的，两个线程也可能导致死锁，只是概率太小，所以需要尝试多次
        * */
        for (int i = 0; i < 100; i++) {
            //1,2要颠倒一下，因为顺序获取两个锁，永远不会发生死锁
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();
        }
    }
}
