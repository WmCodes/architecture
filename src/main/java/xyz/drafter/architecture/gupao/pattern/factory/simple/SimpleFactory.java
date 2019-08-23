package xyz.drafter.architecture.gupao.pattern.factory.simple;

import xyz.drafter.architecture.gupao.pattern.factory.Mengniu;
import xyz.drafter.architecture.gupao.pattern.factory.Milk;
import xyz.drafter.architecture.gupao.pattern.factory.Telunsu;
import xyz.drafter.architecture.gupao.pattern.factory.Yili;

/**
 * @author wangmeng
 * @date 2019/8/22
 * @desciption
 */
public class SimpleFactory {

    public Milk getMilk(String name){
        if ("特仑苏".equals(name)){
            return new Telunsu();
        }else if ("伊利".equals(name)){
            return new Yili();
        }else if ("蒙牛".equals(name)){
            return new Mengniu();
        }
        return null;
    }
}
