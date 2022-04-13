package JUC.Tool;

import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;

/**
 * @Author zzguo
 * @Description 同步信号量
 * @Date 2022/4/12
 **/
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread() + "thread start...");
                    sleep(1);
                    System.out.println(Thread.currentThread() + "thread end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release();
                }

            }).start();
        }
    }
}
