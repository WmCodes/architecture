package xyz.drafter.architecture.gupao.pattern.observer.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmeng
 * @date 2019/9/2
 * @desciption
 */
public class EventLisenter {


    protected Map<Enum,Event> eventMap = new HashMap<>();

    public void addLisenter(Enum eventType, Object target, Method callback){
        //注册事件
        // 用反射调用这个方法
        eventMap.put(eventType, new Event(target, callback));
    }

    private void trigger(Event e){
        e.setSource(this);
        try {
            e.getCallback().invoke(e.getTarget(), e);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }

    }


    protected void trigger(Enum call){
        if (!this.eventMap.containsKey(call)){
            return;
        }
        trigger(this.eventMap.get(call).setTrigger(call.toString()));
    }
}
