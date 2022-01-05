package JUC.Lock.LockSupport;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author zzguo
 * @Description park/unpark 测试
 * @Description 一次park函数过程 park(第一次setBlocker(设置Blocker为指定值)，然后执行unsafe.park()方法) -> unpark(第二次执行setBlocker(设置Blocker为null))
 * @Date 2022/1/4
 **/
class MyThread extends Thread {
    private Object object;

    public MyThread(Object object){
        this.object = object;
    }

    public void run(){
        System.out.println("---before unpark ---");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 获取blocker
        System.out.println("Blocker info," + LockSupport.getBlocker((Thread) object));
        // 释放
        LockSupport.unpark((Thread) object);
        try {
            // 保证执行 park中的serBlocker(t,null)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 再次获取blocker
        System.out.println("Blocker info," + LockSupport.getBlocker((Thread) object));

        System.out.println("---after unpark---");
    }
}

public class ParkTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(Thread.currentThread());
        myThread.start();
        System.out.println("---before park---");
        // 获取许可
        LockSupport.park("PPPPark");
        System.out.println("---after park---");
    }
}
