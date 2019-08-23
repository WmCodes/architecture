package xyz.drafter.architecture.gupao.pattern.prototype.simple;

import java.util.ArrayList;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption 浅复制 ，属性完全一样，引用复制
 */
public class CloneTest {

    public static void main(String[] args) {

        Prototype p = new Prototype();
        p.name = "test";
        p.list = new ArrayList<>();
        p.list.add(new CloneTarget());
        System.out.println(p.list);
        System.out.println(p);
        p.name="test2";
        try {
            Prototype o = (Prototype)p.clone();
            System.out.println(o.name);
            System.out.println(o.list);
            System.out.println(o);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
