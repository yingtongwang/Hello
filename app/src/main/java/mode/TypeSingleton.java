package mode;

/**
 * Created by Wangyingbao on 2016/7/13.
 */
public class TypeSingleton {
    private static TypeSingleton typeSingleton = null;


    /**
     * 标准模式
     * @return
     */
    public static TypeSingleton getTypeSingleton() {
        if (typeSingleton == null) {
            synchronized (TypeSingleton.class) {
                if (typeSingleton == null) {
                    typeSingleton = new TypeSingleton();
                }
            }
        }
        return typeSingleton;
    }
}
