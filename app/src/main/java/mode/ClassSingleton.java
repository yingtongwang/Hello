package mode;

/**
 * Created by Wangyingbao on 2016/7/13.
 */
public class ClassSingleton {
    private static ClassSingleton classSingleton = new ClassSingleton();

    public static ClassSingleton getClassSingleton() {
        return classSingleton;
    }
}
