package xyz.drafter.architecture.gupao.pattern.factory.abstr;

import xyz.drafter.architecture.gupao.pattern.factory.Milk;
import xyz.drafter.architecture.gupao.pattern.factory.func.MengniuFactory;
import xyz.drafter.architecture.gupao.pattern.factory.func.TelunsuFactory;
import xyz.drafter.architecture.gupao.pattern.factory.func.YiliFactory;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class MilkFactory extends AbStractFactory {
    @Override
    public Milk getMengniu() {
        return new MengniuFactory().getMilk();
    }

    @Override
    public Milk getYili() {
        return new YiliFactory().getMilk();
    }

    @Override
    public Milk getTelunsu() {
        return new TelunsuFactory().getMilk();
    }
}
