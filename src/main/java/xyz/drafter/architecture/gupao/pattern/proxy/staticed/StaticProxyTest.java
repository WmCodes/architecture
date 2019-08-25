package xyz.drafter.architecture.gupao.pattern.proxy.staticed;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class StaticProxyTest {

    public static void main(String[] args) {

        Son son = new Son();
        Father father = new Father(son);
        // 只能帮一个人找对象，无法扩展
        father.findLove();
    }
}
