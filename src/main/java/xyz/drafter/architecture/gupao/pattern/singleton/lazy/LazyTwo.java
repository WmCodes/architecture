package xyz.drafter.architecture.gupao.pattern.singleton.lazy;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class LazyTwo {

    private LazyTwo(){}


    private static LazyTwo LazyTwo = null;
    public static synchronized LazyTwo getInstance(){
        if (LazyTwo == null){
            LazyTwo = new LazyTwo();
        }
        return LazyTwo;
    }
}
