package JUC.Lock.CustomizedLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author zzguo
 * @Description 自定义锁测试类
 * @Date 2022/1/5
 **/
public class LazyLockTest {
    public static void main(String[] args) {
        LazyLock lazyLock = new LazyLock();

        new Thread(() -> {
            lazyLock.lock();
            // 不可重入锁
            // System.out.println("lock Once");
            // lazyLock.lock();
            System.out.println("Thread name" + Thread.currentThread().getName() + " lock done");

            lazyLock.unlock();
            System.out.println("Thread name" + Thread.currentThread().getName() + " unlock");

        },"thread1").start();


        new Thread(() -> {
            lazyLock.lock();
            System.out.println("Thread name" + Thread.currentThread().getName() + " lock done");

            lazyLock.unlock();
            System.out.println("Thread name" + Thread.currentThread().getName() + " unlock");

        },"thread2").start();
    }
}


