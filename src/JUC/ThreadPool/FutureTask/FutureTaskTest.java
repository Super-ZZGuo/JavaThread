package JUC.ThreadPool.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author zzguo
 * @Description FutureTask + Thread 测试
 * @Date 2022/1/5
 **/
public class FutureTaskTest {

    // 1、Task继承Callable接口，实现call()方法,泛型参数为返回参数
    static class Task implements Callable<Integer>{
        @Override
        public Integer call() throws InterruptedException {
            System.out.println("Thread " + Thread.currentThread().getName() + "is runnning!");
            int result = 0;
            for (int i = 0 ; i< 100; i++)
                result += i;
            Thread.sleep(5000);
            return result;
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 2、新建FutureTask，需要一个实现了Callable接口的类实例作为构造函数参数
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        // 3、新键Thread对象并run
        Thread thread = new Thread(futureTask);
        thread.setName("My Task thread");
        thread.start();

        Thread.sleep(1000);

        System.out.println("Thread " + Thread.currentThread().getName() + "is runnning!");

        // 4. 调用isDone()判断任务是否结束
        if(!futureTask.isDone()) {
            System.out.println("Task is not done");
            Thread.sleep(2000);
        }
        int result = 0;

        // 5. 调用get()方法获取任务结果,如果任务没有执行完成则阻塞等待
        result = futureTask.get();


        System.out.println("result is " + result);
    }
}
