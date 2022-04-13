package JUC.Tool;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

/**
 * @Author zzguo
 * @Description CountDownLatch
 * @Date 2022/4/12
 **/
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Thread(() -> {
            System.out.println(Thread.currentThread() + "thread start...");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread() + "thread end..." + countDownLatch.getCount());
        }).start();


        new Thread(() -> {
            System.out.println(Thread.currentThread() + "thread start...");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread() + "thread end..." + countDownLatch.getCount());
        }).start();
        new Thread(() -> {
            System.out.println(Thread.currentThread() + "thread start...");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread() + "thread end..." + countDownLatch.getCount());
        }).start();

        System.out.println("waiting...");
        countDownLatch.await();
        System.out.println("ending...");

    }
}
