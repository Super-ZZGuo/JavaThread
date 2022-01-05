package JUC.Lock.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zzguo
 * @Description 可重入锁 公平锁测试
 * @Date 2022/1/5
 **/
class MyRTLThread extends Thread {
    private Lock lock;
    public MyRTLThread(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }

    public void run () {
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);

        // 输出顺序一次为t1 t2 t3
        MyRTLThread t1 = new MyRTLThread("t1", lock);
        MyRTLThread t2 = new MyRTLThread("t2", lock);
        MyRTLThread t3 = new MyRTLThread("t3", lock);
        t1.start();
        t2.start();
        t3.start();
    }
}
