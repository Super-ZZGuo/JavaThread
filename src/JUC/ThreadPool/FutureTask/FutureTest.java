package JUC.ThreadPool.FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author zzguo
 * @Description  Future + ExecutorService测试
 * @Date 2022/1/5
 **/
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                // 开始时间
                Long start = System.currentTimeMillis();
                while (true) {
                    // 当前时间
                    Long current = System.currentTimeMillis();
                    if ((current - start) > 1000) {
                        return 1;
                    }
                }
            }
        });

        try {
            Integer result = (Integer)future.get();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
