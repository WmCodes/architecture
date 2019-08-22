package xyz.drafter.architecture.gupao.pattern.factory.simple;

import xyz.drafter.architecture.gupao.pattern.factory.Milk;
import xyz.drafter.architecture.gupao.pattern.factory.Telunsu;

/**
 * @author wangmeng
 * @date 2019/8/22
 * @desciption
 */
public class SimpleFactory {

    public Milk getMilk(String name){
        if ("特仑苏".equals(name)){
            return new Telunsu();
        }
        return null;
    }
}
