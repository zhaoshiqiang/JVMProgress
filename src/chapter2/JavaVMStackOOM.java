package chapter2;

/**
 * 在Windows平台的虚拟机中，java线程是映射在操作系统内核线程上的
 * 执行此代码可能会导致操作系统死机
 * VM args: -verbose:gc -Xss4M（这时候不妨设置大些）
 * Created by zhaoshiqiang on 2017/7/22.
 */
public class JavaVMStackOOM {
    private void dontStop(){
        while (true){

        }


    }
    
    public void stackLeakByThread(){
        while (true){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            t.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
