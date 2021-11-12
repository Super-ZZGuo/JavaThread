package thread;/*
@date 2021/11/12 - 7:56 下午
*/

public class LazyManModel {
}

class LazyMan{
    private LazyMan(){}

    private static LazyMan instance = null;

    public static LazyMan getInstance(){
        //这一重检查提高了懒汉式的效率
        if(instance == null){
            synchronized (LazyMan.class){
                if(instance == null){
                    instance = new LazyMan();
                }
            }
        }
        return instance;
    }
}
