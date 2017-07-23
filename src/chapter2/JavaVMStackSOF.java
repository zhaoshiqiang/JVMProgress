package chapter2;

/**
 * 对于HotSpot来说，-Xss参数（设置本地方法栈大小）是无效的，因为HotSpot虚拟机不区分虚拟机栈和本地方法栈
 * 在单线程下，无论栈太大，或是虚拟机栈容量太小，当内存无法分配的时候，都是抛出{@link StackOverflowError}
 * VM args: -verbose:gc -Xss128K
 * Created by zhaoshiqiang on 2017/7/22.
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
