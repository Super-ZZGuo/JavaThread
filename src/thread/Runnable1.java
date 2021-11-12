package thread;
/*
@date 2021/11/9 - 10:03 下午
*/


class MyThread implements Runnable{

    private int num = 10000;
    Object object = new Object();

    @Override
    public void run(){
        while(true){
            synchronized (object){
                if(num > 0){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    num--;
                    System.out.println(Thread.currentThread().getName() + "票数剩下" + num);
                }else{
                    break;
                }
            }
        }
    }
}
public class Runnable1  {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        Thread thread1 = new Thread(myThread);
        Thread thread2 = new Thread(myThread);
        Thread thread3 = new Thread(myThread);

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(Thread.currentThread().getName() + "主线程结束");
    }

}
