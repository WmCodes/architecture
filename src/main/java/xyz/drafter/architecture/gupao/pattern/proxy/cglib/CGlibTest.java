package xyz.drafter.architecture.gupao.pattern.proxy.cglib;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class CGlibTest {

    public static void main(String[] args) throws Exception {
        ZhangSan obj = (ZhangSan)new CGlibMeipo().getInstance(ZhangSan.class);
        obj.findLove();
       // System.out.println(obj);
        System.out.println(obj.getClass());
    }
}
