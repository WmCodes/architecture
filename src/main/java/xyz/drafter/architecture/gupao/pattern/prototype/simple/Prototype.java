package xyz.drafter.architecture.gupao.pattern.prototype.simple;

import java.util.ArrayList;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class Prototype implements Cloneable {

    public String name;

    public ArrayList<CloneTarget> list;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
