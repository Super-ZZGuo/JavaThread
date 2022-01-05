package JUC.Lock.AbstractQueuedSynchronizer;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zzguo
 * @Description 基于AQS的ReentrantLock测试
 * @Date 2022/1/5
 **/
class MyAQSThread extends Thread{
    private Lock lock;
    public MyAQSThread(String name, Lock lock) {
        super(name);
        this.lock = lock;
    }
    public void run(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread() + " running");
        } finally {
            lock.unlock();
        }
    }
}
public class AbstractQueuedSynchronizerTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        // 默认构造 非公平锁 因此输出顺序不定
        MyAQSThread t1 = new MyAQSThread("t1", lock);
        MyAQSThread t2 = new MyAQSThread("t2", lock);
        t1.start();
        t2.start();
    }
}
