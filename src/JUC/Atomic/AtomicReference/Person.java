package JUC.Atomic.AtomicReference;

/**
 * @Author zzguo
 * @Description Person 对象
 * @Date 2022/1/2
 **/
public class Person {
    volatile long id;
    public Person(long id) {
        this.id = id;
    }
    public String toString() {
        return "id:"+id;
    }
}
