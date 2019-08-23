package xyz.drafter.architecture.gupao.pattern.factory.abstr;

import xyz.drafter.architecture.gupao.pattern.factory.Milk;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public abstract class AbStractFactory {

    public abstract Milk getMengniu();
    public abstract Milk getYili();
    public abstract Milk getTelunsu();

}
