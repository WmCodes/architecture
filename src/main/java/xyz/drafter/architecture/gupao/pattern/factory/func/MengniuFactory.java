package xyz.drafter.architecture.gupao.pattern.factory.func;

import xyz.drafter.architecture.gupao.pattern.factory.Mengniu;
import xyz.drafter.architecture.gupao.pattern.factory.Milk;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class MengniuFactory implements Factory {
    @Override
    public Milk getMilk() {
        return new Mengniu();
    }
}
