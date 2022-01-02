package JUC.Atomic.AtomicIntegerFieldUpdater;

/**
 * @Author zzguo
 * @Description 测试数据
 * @Date 2022/1/2
 **/
public class Data {

    // 更新字段必须用 public volatile修饰 -> 在线程之间共享变量时保证立即可见
    public volatile int publicVal=3;

    protected volatile int protectedVal=4;

    private volatile  int privateVal=5;

    public volatile static int staticVal = 10;

    //public  final int finalVal = 11;

    public volatile Integer integerVal = 19;
    public volatile Long longVal = 18L;
}
