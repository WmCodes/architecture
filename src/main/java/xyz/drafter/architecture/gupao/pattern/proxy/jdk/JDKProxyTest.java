package xyz.drafter.architecture.gupao.pattern.proxy.jdk;

import xyz.drafter.architecture.gupao.pattern.proxy.staticed.Person;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class JDKProxyTest {

    public static void main(String[] args) throws Exception {
        // 需要转成接口
        Person xieMu = (Person) new JDKMeipo().getInstance(new XieMu());
        xieMu.findLove();

    }
}
