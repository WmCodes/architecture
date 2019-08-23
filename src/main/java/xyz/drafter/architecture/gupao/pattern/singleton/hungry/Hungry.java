package xyz.drafter.architecture.gupao.pattern.singleton.hungry;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class Hungry {


    private Hungry(){};

    private static final Hungry hungry = new Hungry();
    public static Hungry getInstance(){
        System.out.println(System.currentTimeMillis()+":"+hungry);
        return hungry;
    }
}
