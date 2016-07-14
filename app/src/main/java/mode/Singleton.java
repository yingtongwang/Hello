package mode;

/**
 * Created by Wangyingbao on 2016/7/13.
 */
public class Singleton {
    private static Singleton singleton;

    /**
     * 构造函数
     */
    private Singleton() {
    }

    /**
     * 懒汉模式
     *
     * @return
     */
    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
