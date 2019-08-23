package xyz.drafter.architecture.gupao.pattern.singleton.lazy;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class LazyThree {

    private LazyThree(){};

    public static final LazyThree getInstance(){
        return LazyHolder.LAZY;
    }

    private static class LazyHolder{
        private static final LazyThree LAZY = new LazyThree();

    }
}
