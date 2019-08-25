package xyz.drafter.architecture.gupao.pattern.proxy.jdk;

import xyz.drafter.architecture.gupao.pattern.proxy.staticed.Person;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class XieMu  implements Person {

    @Override
    public void findLove(){
        System.out.println("找对象！！！！");
    }
}
