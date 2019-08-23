package xyz.drafter.architecture.gupao.pattern.singleton.lazy;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class LazyOne {

    private LazyOne(){}


    private static LazyOne lazyOne = null;
    public static LazyOne getInstance(){
        if (lazyOne == null){
            lazyOne = new LazyOne();
        }
        return lazyOne;
    }
}
