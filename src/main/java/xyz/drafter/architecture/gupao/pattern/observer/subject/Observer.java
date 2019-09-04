package xyz.drafter.architecture.gupao.pattern.observer.subject;

import xyz.drafter.architecture.gupao.pattern.observer.core.Event;

/**
 * @author wangmeng
 * @date 2019/9/2
 * @desciption
 */
public class Observer {

    public void advice(Event e){

        System.out.println("===触发事件==="+e);


    }
}
