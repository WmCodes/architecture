package xyz.drafter.architecture.gupao.pattern.observer.subject;

import xyz.drafter.architecture.gupao.pattern.observer.core.EventLisenter;

/**
 * @author wangmeng
 * @date 2019/9/2
 * @desciption
 */
public class Subject extends EventLisenter {

    public void add(){
        System.out.println("调用添加方法");
        trigger(SubjectEventType.ON_ADD);

    }


    public void remove(){
        System.out.println("调用删除方法");
        trigger(SubjectEventType.ON_REMOVE);
    }
}
