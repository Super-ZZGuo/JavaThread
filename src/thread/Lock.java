package thread;/*
@date 2021/11/12 - 8:42 下午
*/

import java.util.concurrent.locks.ReentrantLock;

class MyLockThread implements Runnable{

    private int num = 1000;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run(){
        while(true){
            //加锁
            lock.lock();
            try{
                if(num > 0){
                    try {
                        Thread.sleep(1);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    num--;
                    System.out.println(Thread.currentThread().getName() + "数字剩下" + num);
                }else{
                    break;
                }

            }finally{
                //解锁
                lock.unlock();
            }

        }
    }
}
public class Lock  {
    public static void main(String[] args) {

        MyLockThread myLockThread = new MyLockThread();
        Thread thread1 = new Thread(myLockThread);
        Thread thread2 = new Thread(myLockThread);
        Thread thread3 = new Thread(myLockThread);

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(Thread.currentThread().getName() + "主线程结束");
    }

}