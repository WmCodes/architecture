package xyz.drafter.architecture.gupao.springV2.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangmeng
 * @date 2019/9/11
 * @desciption
 */
public class GPAopConfig {

    private Map<Method,GPAspect> points = new HashMap<>();

    public void put(Method target,Object aspect,Method[] points){
        this.points.put(target,new GPAspect(aspect, points));
    }

    public GPAspect get(Method method){
        return this.points.get(method);
    }

    public boolean contains(Method method){
        return this.points.containsKey(method);
    }

    public class GPAspect{
        private Object aspect;
        private Method[] points;
        public GPAspect(Object aspect,Method[] points){
            this.aspect = aspect;
            this.points = points;
        }

        public Object getAspect() {
            return aspect;
        }

        public Method[] getPoints() {
            return points;
        }
    }
}
