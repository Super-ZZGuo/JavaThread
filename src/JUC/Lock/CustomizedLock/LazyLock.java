package JUC.Lock.CustomizedLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author zzguo
 * @Description 自定义锁 懒锁 不可重入锁
 * @Date 2022/1/5
 **/
class LazyLock implements Lock {
    // 自定义懒锁 同步
    class LazySync extends AbstractQueuedSynchronizer {

        @Override //该线程是否正在独占资源。只有用到condition才需要去实现它
        protected boolean isHeldExclusively() {
            // 独占资源即加锁成功 返回true
            return getState() == 1;
        }

        @Override //独占方式。尝试获取资源，成功则返回true，失败则返回false。
        protected boolean tryAcquire(int arg){
            if(compareAndSetState(0,1)){
                // 尝试加锁成功后 设置Owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override //独占方式。尝试释放资源，成功则返回true，失败则返回false。
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            // State 0 表示未加锁 1表示已加锁
            setState(0);
            return false;
        }

        public ConditionObject newConditionObject(){
            return new ConditionObject();
        }

    }

    private LazySync lazySync = new LazySync();
    @Override // 加锁 不成功进入sync queue
    public void lock() {
        lazySync.acquire(1);
    }

    @Override // 加锁，可以被打断
    public void lockInterruptibly() throws InterruptedException {
        lazySync.acquireInterruptibly(1);
    }

    @Override // 尝试加锁(一次)
    public boolean tryLock() {
        return lazySync.tryAcquire(1);
    }

    @Override // 尝试加锁(带超时)
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return lazySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override // 解锁
    public void unlock() {
        lazySync.release(1);
    }

    @Override // 创建锁 条件变量
    public Condition newCondition() {
        return lazySync.newConditionObject();
    }
}
