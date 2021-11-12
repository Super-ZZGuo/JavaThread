package thread;/*
@date 2021/11/12 - 9:18 下午
*/

/**
 * notify()、notifyall()、wait()的调用这必须是同步代码块或者同步方法中的同步监视器
 * 否则会出现java.lang.IllegalMonitorStateException报错
 */
class MyComThread implements Runnable{

    private int num = 100;
    Object object = new Object();

    @Override
    public void run(){
        while(true){
            synchronized (object){
                //唤醒随机一个线程
                object.notify();
                if(num > 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num--;
                    System.out.println(Thread.currentThread().getName() + "打印数字：" + num);
                    try {
                        //线程释放锁，并进入等待队列
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}
public class ThreadCommunication  {
    public static void main(String[] args) {

        MyComThread myThread = new MyComThread();
        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread);
//        Thread thread3 = new Thread(myThread);

        thread1.start();
        thread2.start();
//        thread3.start();
        System.out.println(Thread.currentThread().getName() + "主线程结束");
    }

}
