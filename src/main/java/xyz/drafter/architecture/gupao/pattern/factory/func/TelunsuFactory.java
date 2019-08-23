package xyz.drafter.architecture.gupao.pattern.factory.func;

import xyz.drafter.architecture.gupao.pattern.factory.Milk;
import xyz.drafter.architecture.gupao.pattern.factory.Telunsu;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class TelunsuFactory implements  Factory {
    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}
