package xyz.drafter.architecture.gupao.pattern.singleton.lazy;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class LazyFour {
    private static LazyFour ourInstance = new LazyFour();

    public static LazyFour getInstance() {
        return ourInstance;
    }

    private LazyFour() {
    }
}
