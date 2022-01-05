package JUC.Lock.Atomic.AtomicStampedReference;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author zzguo
 * @Description  原子更新带有 版本号 的引用类型 (解决ABA问题)
 * @Date 2022/1/3
 **/
public class AtomicStampedReferenceTest {

    // 初始化值为 10 版本号为0
    // 操作线程修改值 10 -> 20 -> 10
    // stamp变化    0 -> 1 -> 2
    private static AtomicStampedReference<Integer> ASR =
            new AtomicStampedReference<>(10,0);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            // 获取当前版本号
            int stamp = ASR.getStamp();
            // 获取当前初始化值
            int reference = ASR.getReference();

            System.out.println("操作线程" + Thread.currentThread() + ",初始化值为" + reference + ",版本号为" + stamp);


            try {
                // 主线程等待5秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean isCAS = ASR.compareAndSet(10,20,stamp,stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",CAS操作结果 " +  isCAS);
        },"主线程");

        Thread other = new Thread(() -> {
            // 保证主线程先操作
            Thread.yield();

            boolean isOtherCAS1 = ASR.compareAndSet(10,20,ASR.getStamp(),ASR.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",值为" + ASR.getReference() + ",版本号为" + ASR.getStamp() + ",CAS是否成功" + isOtherCAS1);

            boolean isOtherCAS2 = ASR.compareAndSet(20,10,ASR.getStamp(),ASR.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",值为" + ASR.getReference() + ",版本号为" + ASR.getStamp() + ",CAS是否成功" + isOtherCAS2);
        },"其他线程");

        main.start();
        other.start();
    }



}
