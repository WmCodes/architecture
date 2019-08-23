package xyz.drafter.architecture.gupao.pattern.factory.func;

import xyz.drafter.architecture.gupao.pattern.factory.Milk;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public interface Factory  {

    //工厂必然具有生产产品的技能
    Milk getMilk();
}
