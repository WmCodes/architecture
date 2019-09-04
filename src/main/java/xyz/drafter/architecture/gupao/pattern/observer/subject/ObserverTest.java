package xyz.drafter.architecture.gupao.pattern.observer.subject;

import xyz.drafter.architecture.gupao.pattern.observer.core.Event;

import java.lang.reflect.Method;

/**
 * @author wangmeng
 * @date 2019/9/2
 * @desciption 观察者模式
 */
public class ObserverTest {

    public static void main(String[] args) {

        try {
            // 观察者
            Observer observer = new Observer();
            Method advice = Observer.class.getMethod("advice", new Class<?>[]{Event.class});

            // 被观察者
            Subject subject = new Subject();
            subject.addLisenter(SubjectEventType.ON_ADD, observer,advice);

            subject.add();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
