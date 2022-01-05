package JUC.Lock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author zzguo
 * @Description 线程-读操作
 * @Date 2022/1/5
 **/
class ReadThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public ReadThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " 尝式上锁。。。");
        try {
            rrwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "上锁成功！");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rrwLock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " 解锁成功");
        }
    }
}

/**
 * @Author zzguo
 * @Description 线程-写操作
 * @Date 2022/1/5
 **/
class WriteThread extends Thread {
    private ReentrantReadWriteLock rrwLock;

    public WriteThread(String name, ReentrantReadWriteLock rrwLock) {
        super(name);
        this.rrwLock = rrwLock;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " 尝式上锁。。。");
        try {
            rrwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 上锁成功！");
        } finally {
            rrwLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " 解锁成功");
        }
    }
}

public class ReentrantReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();
        ReadThread rt1 = new ReadThread("线程-读操作-1号", rrwLock);
        ReadThread rt2 = new ReadThread("线程-读操作-2号", rrwLock);
        WriteThread wt1 = new WriteThread("线程-写操作-1号", rrwLock);
        rt1.start();
        rt2.start();
        wt1.start();
    }
}