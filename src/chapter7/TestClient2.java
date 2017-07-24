package chapter7;

/**
 * JVM保证<Clinit>()是线程安全，
 * 如果有多个线程同时初始化一个类，
 * 那么只会有一个线程去执行这个类的<Clinit>方法，且这个方法只会执行一遍，其他线程都需要阻塞等待。
 * 如果在这个方法中有耗时很长的操作，就可能造成多个线程阻塞。实际引用中这种阻塞往往很隐蔽
 * Created by zhaoshq on 2017/7/24.
 */
public class TestClient2 {
    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + "run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);

        thread1.start();
        thread2.start();
    }
}

class DeadLoopClass {
    static {
        //如果不加这个if语句，将提示“Initializer does not complete normally”，并拒绝编译
        if (true){
            System.out.println(Thread.currentThread() + "init DeadLoopClass");
            //在<clinit>()方法中有耗时很长的操作
            while (true){

            }
        }
    }


}
