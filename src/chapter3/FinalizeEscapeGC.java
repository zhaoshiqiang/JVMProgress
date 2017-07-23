package chapter3;

/**
 * 一次对象自我拯救的演示
 * 如果覆盖finalize方法，对象在gc时有且只有一次自救机会
 * 不推荐使用finalize，它的运行代价高，不确定性大，无法保证各个对象的调用顺序
 * finalize能做的所有工作，try-finally或者其他方式都可以做得更好
 * Created by zhaoshiqiang on 2017/7/23.
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executor!");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        GCtest();
        //这次自救失败
        GCtest();
    }

    private static void GCtest() throws InterruptedException {
        SAVE_HOOK = null;
        System.gc();
        //finalize方法优先级很低，所以停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no , i am dead");
        }
    }
}
