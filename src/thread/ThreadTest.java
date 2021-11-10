package thread;/*
@date 2021/11/9 - 11:46 下午
*/

class MThread extends Thread{
    private int num = 100;

    @Override
    public void run(){
        while(true){
            if(num > 0){
                num--;
                System.out.println(Thread.currentThread().getName() + "票数剩下" + num);
            }else{
                break;
            }
        }
    }
}
public class ThreadTest {
    public static void main(String[] args) {

        MThread myThread1 = new MThread();
        MThread myThread2 = new MThread();
        MThread myThread3 = new MThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
