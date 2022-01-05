package JUC.Lock.Atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Author zzguo
 * @Description 原子更新字段
 * @Date 2022/1/2
 **/
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args){
        AtomicIntegerFieldUpdaterTest IAt = new AtomicIntegerFieldUpdaterTest();
        IAt.doIt();
    }

    public AtomicIntegerFieldUpdater<Data> updater(String name){
        // 创建一个更新器 设置想要更新的 类 和 字段(属性)
        return AtomicIntegerFieldUpdater.newUpdater(Data.class,name);

    }

    public void doIt(){
        Data data = new Data();
        System.out.println("publicVar = "+updater("publicVal").getAndAdd(data, 2));
        // System.out.println("protectedVal = "+updater("protectedVal").getAndAdd(data,2));


        /* 1.
         *   在Data类中属性privateVal,在AtomicIntegerFieldUpdaterTest中不能访问
         *   PS：说调用者能够直接操作对象字段，那么就可以反射进行原子操作，参考此处 public、protect、private
         */
        // System.out.println("privateVal = "+updater("privateVal").getAndAdd(data,2));

        /* 2.
         *   约束：修改字段只能是实例变量，不能是类变量，也就是说不能加static关键字。
         */
        // System.out.println("staticVal = "+updater("staticVar").getAndIncrement(data));

        /* 3.
         *   对应的 integer字段无法 修改 其他字段类型, 如果要修改包装类型就需要使用 AtomicReferenceFieldUpdater。
         */
        System.out.println("integerVar = "+updater("integerVar").getAndIncrement(data));
        System.out.println("longVar = "+updater("longVar").getAndIncrement(data));
    }

}
