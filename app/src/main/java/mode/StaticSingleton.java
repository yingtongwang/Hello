package mode;

/**
 * Created by Wangyingbao on 2016/7/13.
 */
public class StaticSingleton {
    private StaticSingleton() {
    }

    public static StaticSingleton getStaticSingleton() {
        return StaticSingletonHolder.staticSingleton;
    }

    private static class StaticSingletonHolder {
        private static final StaticSingleton staticSingleton = new StaticSingleton();
    }
}
