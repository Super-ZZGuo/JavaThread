package JUC.Atomic.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author zzguo
 * @Description 原子更新引用类型
 * @Date 2022/1/2
 **/
public class AtomicReferenceTest {
    public static void main(String[] args) {

        // 创建两个Person对象，它们的id分别是101和102。
        Person p1 = new Person(101);
        Person p2 = new Person(102);

        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);

        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        ar.compareAndSet(p1, p2);

        // 最后，获取ar对应的对象，并打印结果。
        Person p3 = (Person)ar.get();
        System.out.println("p3 " + p3);
        System.out.println("p3.equals(p1) = " + p3.equals(p1));

        // p3.equals(p1) 的结果为 false ，这是因为 Person 并没有覆盖 equals() 方法，而是采用继承自 Object.java 的 equals() 方法；
        // 而 Object.java 中的 equals() 实际上是调用 " == " 去比较两个对象，即比较两个对象的地址是否相等。
    }
}
